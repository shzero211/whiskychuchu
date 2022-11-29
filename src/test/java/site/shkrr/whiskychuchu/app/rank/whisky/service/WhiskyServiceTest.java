package site.shkrr.whiskychuchu.app.rank.whisky.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WhiskyServiceTest {
    @Autowired
    private WhiskyService whiskyService;
    @Test
    @Rollback(value = false)
    public void t1(){
    whiskyService.crawlingAndSave();
    }
}