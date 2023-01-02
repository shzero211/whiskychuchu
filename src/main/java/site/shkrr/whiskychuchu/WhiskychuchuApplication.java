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
			System.setProperty("webdriver.chrome.driver","/chromedriver_win32/chromedriver.exe");
			System.setProperty("file.dir","C:/whisky/");
		}else{
			System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");// 앞에 /없으면 실행 경로 부터 찾음 (중요),/있으면 절대경로로 찾음
			System.setProperty("file.dir"," /home/ec2-user/app/step1/");
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(WhiskychuchuApplication.class, args);
	}

}
