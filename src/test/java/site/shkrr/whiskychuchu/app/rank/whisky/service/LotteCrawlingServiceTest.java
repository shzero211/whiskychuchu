package site.shkrr.whiskychuchu.app.rank.whisky.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.shkrr.whiskychuchu.app.rank.whisky.service.CrawlingService;

@SpringBootTest
class LotteCrawlingServiceTest {
    @Autowired
    private CrawlingService crawlingService;
    @Test
    public void t1(){
        crawlingService.crawling();
    }
}