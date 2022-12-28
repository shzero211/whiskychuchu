package site.shkrr.whiskychuchu.app.rank.whisky.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class WhiskyCustomRepositoryImplTest {
    @Autowired
    private WhiskyRepository whiskyRepository;
    @Test
    @DisplayName("위스키 saleRank 초기화 메서드 테스트")
    public void t1(){
        whiskyRepository.resetAllSaleRank();
        Whisky whisky=whiskyRepository.findById(1L).orElse(null);
        Assertions.assertEquals(whisky.getSaleRank(),0L);
    }
}