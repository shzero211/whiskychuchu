package site.shkrr.whiskychuchu.app.rank.whisky.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;
import site.shkrr.whiskychuchu.app.rank.whisky.repository.WhiskyRepository;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Disabled
class WhiskyImgServiceTest {
    @Autowired
    private WhiskyRepository whiskyRepository;
    @Autowired
    private  WhiskyImgService whiskyImgService;
    @Test
    public void t1() throws IOException {
        Whisky whisky=whiskyRepository.save(Whisky.builder()
                        .saleRank(1L)
                        .perPrice(1000)
                        .price(10000)
                        .name("짐빔")
                .build());
        whiskyImgService.saveCrawledWhiskyImg(whisky,"https://contents.lotteon.com/itemimage/_v031642/LM/00/80/68/60/01/40/9_/00/1/LM0080686001409_001_1.jpg/dims/optimize/dims/resizemc/360x360");
    }
}