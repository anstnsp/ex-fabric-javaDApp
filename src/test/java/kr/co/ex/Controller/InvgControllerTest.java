// package kr.co.ex.Controller;

// import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;

// import kr.co.ex.domain.insinfo.InsInfoRepository;

// @RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// public class InvgControllerTest {
  
//   @Autowired
//   private InsInfoRepository insInfoRepository; 

//   @Test
//   public void 조회시간테스트() {
//     Long start = System.currentTimeMillis(); 
    
//     insInfoRepository.findDynamicQuery("20200901", "0001", "1234", "1", "Y", "");
    
//     Long end = System.currentTimeMillis();
//     System.out.println( (end - start) / 1000);

//   }
// }