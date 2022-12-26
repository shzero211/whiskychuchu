package site.shkrr.whiskychuchu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Locale;

@EnableScheduling
@SpringBootApplication
public class WhiskychuchuApplication {
	//application.yml 이 적용되기전에 먼저 환경변수와 적용되야하는 속성이기때문에 이렇게 설정해주었다.
	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
		String os=System.getProperty("os.name").toLowerCase();
		if(os.contains("win")){
			System.setProperty("webdriver.chrome.driver","C:/Users/KIM/IdeaProjects/whiskychuchu/src/main/resources/static/chromedriver_win32/chromedriver.exe");
		}else{
			System.setProperty("webdriver.chrome.driver","file:/usr/bin/chromedriver.exe");
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(WhiskychuchuApplication.class, args);
	}

}
