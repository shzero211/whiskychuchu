package site.shkrr.whiskychuchu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WhiskychuchuApplication {
	//application.yml 이 적용되기전에 먼저 환경변수와 적용되야하는 속성이기때문에 이렇게 설정해주었다.
	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
	}
	public static void main(String[] args) {
		SpringApplication.run(WhiskychuchuApplication.class, args);
	}

}
