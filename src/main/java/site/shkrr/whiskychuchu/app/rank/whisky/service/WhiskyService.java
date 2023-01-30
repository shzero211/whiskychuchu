package site.shkrr.whiskychuchu.app.rank.whisky.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.*;
import site.shkrr.whiskychuchu.app.rank.whisky.repository.WhiskyRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WhiskyService {
    private final CrawlingService crawlingService;
    private final WhiskyRepository whiskyRepository;

    private final WhiskyImgService whiskyImgService;
    /*
    * 크롤링으로 롯데마트에서 판매하는 위스키정보를 가져온후
    * 이름이 중복된것이 있으면 업데이트해주고
    * 없으면 저장 하는 메소드
    * */
    @Transactional
    @Scheduled(cron = "0 0 4 * * 1")
    public void crawlingAndSave() throws IOException {
        List<CrawledWhiskyData> crawledDatas=crawlingService.crawling();
        whiskyRepository.resetAllSaleRank();//모든 위스키 saleRank 0L 로 초기화
        for(CrawledWhiskyData crawledWhiskyData:crawledDatas){
            Whisky whisky =whiskyRepository.findByName(crawledWhiskyData.getWhiskyName()).orElse(null);
            if(whisky!=null){
                whisky.update(crawledWhiskyData.getWhiskyPrice(),crawledWhiskyData.getWhiskyPerPrice(),crawledWhiskyData.getSaleRank());
                whiskyImgService.updateCrawledWhiskyImg(whisky,crawledWhiskyData.getWhiskyImgUrl());
            }else{
                Whisky savedWhisky=whiskyRepository.save(crawledWhiskyData.toEntity());
                whiskyImgService. saveCrawledWhiskyImg(savedWhisky,crawledWhiskyData.getWhiskyImgUrl());
            }
        }
    }

    public List<AdminWhisky> getAdminWhiskyList() {
        List<AdminWhisky> adminWhiskyList=new ArrayList<>();
        List<Whisky> allWhiskyList=whiskyRepository.findAll(Sort.by(Sort.Direction.ASC, "saleRank"));
        for(Whisky whisky:allWhiskyList){
            adminWhiskyList.add(whisky.toAdminWhisky());
        }
        return adminWhiskyList;
    }

    public AdminWhiskyDetail getAdminWhiskyDetail(Long id) {
        Whisky whisky=whiskyRepository.findById(id).orElseThrow(()->new EntityNotFoundException("관리되고있는 위스키 상세정보가 없습니다."));
        return whisky.toAdminWhiskyDetail();
    }

    @Transactional
    public void updateWhisky(AdminWhiskyDetailReq req, MultipartFile file) {
        Whisky whisky= whiskyRepository.findById(req.getId()).orElseThrow(()->new EntityNotFoundException("수정할 위스키 상세정보가 없습니다."));
        whisky.update(req.getCountryType().trim(), req.getIngredientType().trim(),req.getFlavorType().trim());
        //이미지 파일을 받았다면 파일저장및 DB 정보를 수정해주는 메소드 호출
        if(!file.isEmpty()&&file.getOriginalFilename().length()!=0){
            whiskyImgService.updateWhiskyImg(whisky,file);
        }
    }

    public List<WhiskyMainRankDto> getMainRankListOrderBy(String field){
        return whiskyRepository.getWhiskyMainRankOrderBy(field);
    }

    @Transactional
    public void deleteAdminWhisky(Long id) {
        Whisky whisky= whiskyRepository.findById(id).orElseThrow(()->new EntityNotFoundException("삭제할 위스키 상세정보가 없습니다."));
        whiskyImgService.deleteWhiskyImg(whisky);
        whiskyRepository.delete(whisky);
    }

    public List<AdminOwnerWhisky> getAdminOwnerWhisky() {
        return whiskyRepository.getAdminOwnerWhisky();
    }
    @Transactional
    public void updateOwnerWhisky(List<AdminOwnerWhiskyReq> list) {
        whiskyRepository.resetAllOwnerRank();
        for(int i=0;i<list.size();i++){
            Long id=Long.parseLong(list.get(i).getId());
            Long ownerRank=Long.parseLong(String.valueOf(i))+1;
            String ownerComment=list.get(i).getOwnerComment();
            whiskyRepository.updateOwnerWhisky(id,ownerRank,ownerComment);
        }
    }
}
