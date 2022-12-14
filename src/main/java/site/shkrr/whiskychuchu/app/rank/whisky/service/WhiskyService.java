package site.shkrr.whiskychuchu.app.rank.whisky.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.AdminWhiskyDetailReq;
import site.shkrr.whiskychuchu.app.rank.whisky.repository.WhiskyRepository;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.AdminWhisky;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.AdminWhiskyDetail;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.CrawledWhiskyData;
import site.shkrr.whiskychuchu.app.rank.whisky.repository.dto.WhiskyMainRankDto;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public AdminWhiskyDetail getAdminWhiskyDetail(Long id) {
        Whisky whisky=whiskyRepository.findById(id).orElseThrow(()->new EntityNotFoundException("관리되고있는 위스키 상세정보가 없습니다."));
        return whisky.toAdminWhiskyDetail();
    }

    @Transactional
    public void updateWhisky(AdminWhiskyDetailReq req, MultipartFile file) {
        Whisky whisky= whiskyRepository.findById(req.getId()).orElseThrow(()->new EntityNotFoundException("수정할 위스키 상세정보가 없습니다."));
        whisky.update(req.getCountryType().trim(), req.getIngredientType().trim());
        //이미지 파일을 받았다면 파일저장및 DB 정보를 수정해주는 메소드 호출
        if(!file.isEmpty()&&file.getOriginalFilename().length()!=0){
            whiskyImgService.updateWhiskyImg(whisky,file);
        }
    }

    @Transactional
    public void deleteAdminWhisky(Long id) {
        Whisky whisky= whiskyRepository.findById(id).orElseThrow(()->new EntityNotFoundException("삭제할 위스키 상세정보가 없습니다."));
        whiskyImgService.deleteWhiskyImg(whisky);
        whiskyRepository.delete(whisky);
    }

    public List<WhiskyMainRankDto> getMainRankList(){
        return whiskyRepository.getWhiskyMainRankOrderBySaleRank();
    }

    public List<WhiskyMainRankDto> getMainRankListOrderBy(String field){
        return whiskyRepository.getWhiskyMainRankOrderBy(field);
    }
}
