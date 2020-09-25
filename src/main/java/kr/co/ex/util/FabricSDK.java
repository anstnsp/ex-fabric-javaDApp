package kr.co.ex.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import java.util.HashMap;

import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;

import java.util.Properties;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallet.Identity;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric.sdk.security.CryptoSuiteFactory;
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import kr.co.ex.common.Chaincode;
import kr.co.ex.common.ChaincodeFunction;
import kr.co.ex.common.Channel;
import kr.co.ex.config.HLFProps;
import kr.co.ex.exception.CustomContractException;
import kr.co.ex.exception.NoWalletOrCertException;
import kr.co.ex.fabric.GatewayImpl;
import kr.co.ex.web.dto.response.common.CommonResult;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FabricSDK {

	private static final Logger logger = LoggerFactory.getLogger(FabricSDK.class);
	private final HLFProps hlfProps;
	private Gson gson = new Gson();
	
	public void enrollAdmin() throws Exception {
		try {
			// Create a CA client for interacting with the CA.
			Properties props = new Properties();
			props.put("pemFile", hlfProps.getCaCertPath());
			props.put("allowAllHostNames", "true");
			// orderer CA: 7054 , naver CA: 17054, kakao CA: 27054
			HFCAClient caClient = HFCAClient.createNewInstance(hlfProps.getCaIpPort(), props);
			CryptoSuite cryptoSuite = CryptoSuiteFactory.getDefault().getCryptoSuite();
			caClient.setCryptoSuite(cryptoSuite);

			// Create a wallet for managing identities
			Wallet wallet = Wallet.createFileSystemWallet(Paths.get(hlfProps.getWalletPath()));

			// Check to see if we've already enrolled the admin user.
			boolean adminExists = wallet.exists(hlfProps.getCaEnrollId());
			if (adminExists) {
				logger.warn("An identity for the admin user {} already exists in the wallet", hlfProps.getCaEnrollId());
				return;
			}

			// Enroll the admin user, and import the new identity into the wallet.
			final EnrollmentRequest enrollmentRequestTLS = new EnrollmentRequest();
			// enrollmentRequestTLS.addHost("localhost");
			enrollmentRequestTLS.addHost(hlfProps.getHostIp());
			enrollmentRequestTLS.setProfile("tls");
			Enrollment enrollment = caClient.enroll(hlfProps.getCaEnrollId(), hlfProps.getCaEnrollPw(), enrollmentRequestTLS);
			Identity user = Identity.createIdentity(hlfProps.getMspId(), enrollment.getCert(), enrollment.getKey());
			wallet.put(hlfProps.getCaEnrollId(), user);
			logger.info("Successfully enrolled user {} and imported it into the wallet", hlfProps.getCaEnrollId());
		} catch (Exception e) {
			logger.error("관리자 등록 실패 : {}", e.getMessage(), e);
			throw e;
		}
	}

	private Gateway getHLFGateway() {
		try {
			logger.debug("#### START getHLFGateway ####");
			// Path walletPath = Paths.get(Util.resourcesUrlPath(WALLET_PATH));
			// Path networkConfigPath = Paths.get(Util.resourcesUrlPath(CONNECTION_CONFIG));
			Path walletPath = Paths.get(hlfProps.getWalletPath());
			Path networkConfigPath = Paths.get(hlfProps.getConnectionConfig());
			Wallet wallet = Wallet.createFileSystemWallet(walletPath);

			Gateway.Builder builder = new GatewayImpl.Builder();
			// Gateway.Builder builder = Gateway.createBuilder();
			builder.identity(wallet, hlfProps.getCaEnrollId()).networkConfig(networkConfigPath).discovery(false);
			Gateway gateway = builder.connect();

			return gateway;
		} catch (IOException e) {
			logger.error("[getHLFGateway] IOException :" + e.getMessage(), e);
			throw new NoWalletOrCertException("wallet폴더가 없거나 wallet안에 인증서가 존재하지 않습니다.");
		}
	}

	/**
	 * 
	 * @param channelName   - HLF 채널명
	 * @param chaincodeName - HLF 체인코드명
	 * @return 해당 채널,체인코드명에 해당하는 Contract객체
	 */
	public Contract getContract(Channel channelName, Chaincode chaincodeName) {
		logger.debug("#### START getContract ####");
		Gateway gateway = getHLFGateway();
		Network network = gateway.getNetwork(channelName.getValue());
		return network.getContract(chaincodeName.getValue());
	}

	/**
	 * 
	 * @see 공개+비공개 데이터 모두 invoke 아래는 비공개데이터만 invoke시 예제. ex)
	 *      fabricSDK.invokePrivPubTransaction(ChaincodeFunction.createPrivData,
	 *      privData, "");
	 * @Author 김소정
	 * @param channelName   - HLF 채널명
	 * @param chaincodeName - HLF 체인코드명
	 * @param funcName      - 호출할 체인코드 함수명
	 * @param transientKey  - transient의 key
	 * @param privData      - 체인코드에 넘길 비공개데이터
	 * @param pubData       - 체인코드에 넘길 공개데이터
	 * @throws ContractException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public CommonResult invokePrivPubTransaction(Channel channelName, Chaincode chaincodeName, ChaincodeFunction funcName,
			String transientKey, Object privData, Object pubData)
			throws ContractException, TimeoutException, InterruptedException {

		Contract contract = getContract(channelName, chaincodeName);
		//private data 
		Map<String, byte[]> transMap = new HashMap<>();
		String privDataToString = gson.toJson(privData);
		transMap.put(transientKey, privDataToString.getBytes());
		//public data
		String pubDataToString = gson.toJson(pubData);
		
		byte[] CCResult = contract.createTransaction(funcName.getValue()).setTransient(transMap).submit(pubDataToString);
		logger.debug("인보크 후 결과 값: " + new String(CCResult));
				
		// 결과값 파싱.
		return parseCCResponse(CCResult);
	}

	/**
	 * @see publicTransaction만 invoke처리한다.
	 * @param channelName   - HLF 채널명
	 * @param chaincodeName - HLF 체인코드명
	 * @param funcName      - 호출할 체인코드 함수명
	 * @param pubData       - 체인코드에 넘길 공개데이터
	 * @return CommonResult.class
	 * @throws ContractException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public CommonResult invokePublicTrasaction(Channel channelName, Chaincode chaincodeName, ChaincodeFunction funcName,
			Object pubData) throws ContractException, TimeoutException, InterruptedException {

		Contract contract = getContract(channelName, chaincodeName); // 하이퍼렛저 채널,체인코드에 연결
		String pubDataToString = gson.toJson(pubData);
		byte[] CCResult = contract.submitTransaction(funcName.getValue(), pubDataToString); // 하이퍼렛저에 전달 후 결과 받음.
		logger.debug("인보크 후 결과 값: " + new String(CCResult));

		// 결과값 파싱.
		return parseCCResponse(CCResult);

	}

	/**
	 * @apiNote 공개데이터를 조회한다.
	 * @param channelName   - HLF 채널명
	 * @param chaincodeName - HLF 체인코드명
	 * @param funcName      - 호출할 체인코드 함수명
	 * @param findKey       - 조회 키
	 * @return 조회결과(String)
	 * @throws ContractException
	 * @throws ParseException
	 * @throws IOException
	 */
	public Object evaluateTransaction(Channel channelName, Chaincode chaincodeName, ChaincodeFunction funcName,
			Object findKey) throws ContractException, IOException, ParseException {
				
		Contract contract = getContract(channelName, chaincodeName); // 하이퍼렛저 채널,체인코드에 연결
		byte[] CCResult = contract.evaluateTransaction(funcName.getValue(),gson.toJson(findKey));
		String stringResult = new String(CCResult);

		parseCCResponse(CCResult);

		JSONObject json = Utils.jsonParse(stringResult);
		Object searchData = json.get("data");
		if(searchData == null) {
			return Arrays.asList();
		}
		return searchData; 

	}

	private CommonResult parseCCResponse(byte[] CCResult) {
		CommonResult CCResponse = gson.fromJson(new String(CCResult), CommonResult.class);
		if (CCResponse.getCode() == 9999) return CCResponse; 
		else throw new CustomContractException(CCResponse.getCode(), CCResponse.getMsg()); 
	}

}
