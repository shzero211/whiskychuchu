package site.shkrr.whiskychuchu.app.rank.whisky.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;
import site.shkrr.whiskychuchu.app.rank.whisky.repository.WhiskyRepository;
import site.shkrr.whiskychuchu.app.rank.whisky.service.dto.CrawledWhiskyData;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class WhiskyService {
    private final CrawlingService crawlingService;
    private final WhiskyRepository whiskyRepository;

    public void crawlingAndSave(){
        Set<CrawledWhiskyData> crawledDatas=crawlingService.crawling();
        for(CrawledWhiskyData crawledWhiskyData:crawledDatas){
            Whisky whisky =whiskyRepository.findByName(crawledWhiskyData.getWhiskyName()).orElse(null);
            if(whisky!=null){
                whisky.update(crawledWhiskyData.getWhiskyPrice(),crawledWhiskyData.getWhiskyPerPrice(),crawledWhiskyData.getSaleRank());
                whiskyRepository.save(whisky);
            }else{
                whiskyRepository.save(crawledWhiskyData.toEntity());
            }
        }
    }
}
