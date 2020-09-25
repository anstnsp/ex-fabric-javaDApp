package kr.co.ex.util;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import org.hyperledger.fabric.gateway.spi.Checkpointer;
import org.springframework.stereotype.Component;

import kr.co.ex.domain.checkpoint.CheckPoint;
import kr.co.ex.domain.checkpoint.CheckPointRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CheckpointerImpl implements Checkpointer {

  private final CheckPointRepository checkpointRepository; 

  @Override
  public long getBlockNumber() throws IOException {
    //가장 높은 블럭수를 가져온다. 
    Long blockNumber = checkpointRepository.getMaxBlockNumber();
    if(blockNumber == null) blockNumber = -1L;
    return blockNumber; 
  }

  @Override
  public void setBlockNumber(long blockNumber) throws IOException {
       
  }

  @Override
  public Set<String> getTransactionIds() throws IOException {
    //해당 블럭으로 조회한 트랜잭션 들 
    return checkpointRepository.findByBlockNumber(this.getBlockNumber());
  }

  @Override
  public void close() throws IOException {
  
  }

  @Override
  public void addTransactionId(String transactionId) throws IOException {
 
  }

  public Set<String> getTransactionIds(long blockNumber) {
    return checkpointRepository.findByBlockNumber(blockNumber);
  }

  public void addRow(CheckPoint checkPoint) {
    checkpointRepository.save(checkPoint);
  }

  public Optional<CheckPoint> findByTxId(String txId) {
    return checkpointRepository.findByTxId(txId);
  }
}