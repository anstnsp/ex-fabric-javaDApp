package kr.co.ex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class ExCorporationDAppApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ExCorporationDAppApplication.class);
		app.addListeners(new ApplicationPidFileWriter());
		app.run(args);   
	}
  
}
