package site.shkrr.whiskychuchu.querydsltest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import site.shkrr.whiskychuchu.app.rank.whisky.repository.WhiskyRepository;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.WhiskyMainRankDto;

import java.util.List;
@Slf4j
@SpringBootTest
@Transactional
public class WhiskyQueryDslRepositoryTest {
    @Autowired
    private WhiskyRepository whiskyRepository;
    @Test
    public void t1(){
        List<WhiskyMainRankDto>whiskyMainRankDtos=whiskyRepository.getWhiskyMainRankOrderBySaleRank();
        for(WhiskyMainRankDto whiskyMainRankDto:whiskyMainRankDtos){
            log.info(whiskyMainRankDto.getName());
        }
    }
}
