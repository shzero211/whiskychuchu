package site.shkrr.whiskychuchu.app.rank.whisky.service;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import site.shkrr.whiskychuchu.app.rank.whisky.service.dto.CrawledWhiskyData;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LotteCrawlingService implements CrawlingService {
    List<CrawledWhiskyData> whiskyDatas;
    private final WebDriver webDriver;
    @Value("${url.lotte}")
    private String url;
    private static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static String WEB_DRIVER_PATH = "/chromedriver_win32/chromedriver.exe";

    //생성자로 webDriver 초기설정 + 위스키 정보들을 담을 whiskDatas 초기화
    public LotteCrawlingService(){
        System.setProperty(WEB_DRIVER_ID,WEB_DRIVER_PATH);
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        this.webDriver=new ChromeDriver(options);
        whiskyDatas=new ArrayList<>();
    }
    @Override
    public List<CrawledWhiskyData> crawling(){
        try {
            log.info("페이지 접속");
            webDriver.get(url);
            log.info("페이지 접속후 2초 로딩");
            Thread.sleep(2000);//잠시 페이지 로딩할시간이 필요(없으면 데이터 사용시 오류남)
            WebElement orderBySale=webDriver.findElement(By.cssSelector("ul.srchResultSortOptions > li:nth-child(2)"));//판매많은순 버튼
            orderBySale.click();//클릭
            log.info("다른 페이지이동후 2초 로딩");
            Thread.sleep(2000);//잠시 페이지 로딩할시간이 필요(없으면 데이터 사용시 오류남)
            List<WebElement> siteDetails=webDriver.findElements(By.className("srchProductUnitInfoArea"));//위스키 각자 정보들
            for(WebElement siteDetail:siteDetails){
                WebElement whiskyName =siteDetail.findElement(By.className("srchProductUnitTitle"));
                WebElement whiskyPrice=siteDetail.findElement(By.cssSelector("strong.s-product-price__final>span.s-product-price__number"));
                WebElement whiskyPerPrice=siteDetail.findElement(By.className("s-product-price__unit"));
                whiskyDatas.add(
                        CrawledWhiskyData.builder()
                                .whiskyPrice(whiskyPrice.getText())
                                 .whiskyName(whiskyName.getText())
                                .whiskyPerPrice(whiskyPerPrice.getText())
                                .build()
                );
            }
            log.info(String.valueOf("위스키 데이터 사이즈=>"+whiskyDatas.size()));//정보가 잘 담겼는지 로깅
            log.info(whiskyDatas.get(0).getWhiskyName());//첫 위스키 데이터가 올바르게 들어왔는지 확인
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            webDriver.close();
        }
        return whiskyDatas;
    }
}
