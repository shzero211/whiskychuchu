package site.shkrr.whiskychuchu.app.rank.whisky.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;
import site.shkrr.whiskychuchu.app.rank.whisky.repository.WhiskyRepository;
import site.shkrr.whiskychuchu.app.rank.whisky.service.dto.AdminWhisky;
import site.shkrr.whiskychuchu.app.rank.whisky.service.dto.CrawledWhiskyData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class WhiskyService {
    private final CrawlingService crawlingService;
    private final WhiskyRepository whiskyRepository;
    /*
    * 크롤링으로 롯데마트에서 판매하는 위스키정보를 가져온후
    * 이름이 중복된것이 있으면 업데이트해주고
    * 없으면 저장 하는 메소드
    * */
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

    public List<AdminWhisky> getAdminWhiskyList() {
        List<AdminWhisky> adminWhiskyList=new ArrayList<>();
        List<Whisky> allWhiskyList=whiskyRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        for(Whisky whisky:allWhiskyList){
            adminWhiskyList.add(whisky.toAdminWhisky());
        }
        return adminWhiskyList;
    }

}
