package site.shkrr.whiskychuchu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Locale;

@EnableScheduling
@Slf4j
@SpringBootApplication
public class WhiskychuchuApplication {
	//application.yml 이 적용되기전에 먼저 환경변수와 적용되야하는 속성이기때문에 이렇게 설정해주었다.
	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
		String os=System.getProperty("os.name").toLowerCase();
		if(os.contains("win")){
			System.setProperty("webdriver.chrome.driver","C:\\Users\\KIM\\IdeaProjects\\whiskychuchu\\src\\main\\resources\\static\\chromedriver_win32\\chromedriver.exe");
			log.info("윈도우드라이버 실행 path:"+System.getProperty("webdriver.chrome.driver"));
		}else{
			//System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");// 앞에 /없으면 실행 경로 부터 찾음 (중요),/있으면 절대경로로 찾음
			System.setProperty("webdriver.chrome.driver","/chromedriver_linux64/chromedriver");// 앞에 /없으면 실행 경로 부터 찾음 (중요),/있으면 절대경로로 찾음
			log.info("리눅스 드라이버 실행 path:"+System.getProperty("webdriver.chrome.driver"));
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(WhiskychuchuApplication.class, args);
	}

}
