package site.shkrr.whiskychuchu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import site.shkrr.whiskychuchu.app.global.util.jarfilepath.PathUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
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
			System.setProperty("webdriver.chrome.driver", PathUtil.getResourcePath("static/chromedriver_win32/chromedriver.exe"));
            System.setProperty("file.dir","C:/whisky/");
			log.info( PathUtil.getResourcePath("static/chromedriver_win32/chromedriver.exe"));
		}else{
			//System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");// 앞에 /없으면 실행 경로 부터 찾음 (중요),/있으면 절대경로로 찾음
			System.setProperty("webdriver.chrome.driver", PathUtil.getJarResourcePath("/static/chromedriver_linux64/chromedriver"));// 앞에 /없으면 실행 경로 부터 찾음 (중요),/있으면 절대경로로 찾음
			System.setProperty("file.dir","/usr/bin/dummy/");
			log.info( PathUtil.getJarResourcePath("/static/chromedriver_linux64/chromedriver"));
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(WhiskychuchuApplication.class, args);
	}

}
