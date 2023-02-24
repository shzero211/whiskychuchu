package site.shkrr.whiskychuchu.app.rank.whisky.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import site.shkrr.whiskychuchu.rank.domain.whisky.Whisky;
import site.shkrr.whiskychuchu.rank.controller.dto.AdminOwnerWhiskyReq;
import site.shkrr.whiskychuchu.rank.controller.dto.AdminWhiskyDetailReq;
import site.shkrr.whiskychuchu.rank.domain.whisky.enums.CountryType;
import site.shkrr.whiskychuchu.rank.domain.whisky.enums.FlavorType;
import site.shkrr.whiskychuchu.rank.domain.whisky.enums.IngredientType;
import site.shkrr.whiskychuchu.rank.domain.whisky.repository.WhiskyRepository;
import site.shkrr.whiskychuchu.rank.service.whisky.WhiskyService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
class WhiskyServiceTest {
    @Autowired
    private WhiskyService whiskyService;
    @Autowired
    private WhiskyRepository whiskyRepository;

    @Autowired
    ResourceLoader loader;

    @Test
    @DisplayName("위스키 엔티티 수정시 URL 이미지 정보로 이미지 수정할수 있는지 테스트")
    @Disabled
    public void t2() throws IOException {
        String imgUrl="https://contents.lotteon.com/itemimage/_v095758/LM/49/01/77/71/69/04/3_/00/1/LM4901777169043_001_1.jpg/dims/optimize/dims/resizemc/360x360";
        Resource resource =loader.getResource(imgUrl);
        MockMultipartFile file=new MockMultipartFile("file","whiskyImg.jpg",null,resource.getInputStream());
        AdminWhiskyDetailReq req=AdminWhiskyDetailReq.builder()
                .id(5L)
                .countryType(CountryType.미국.toString())
                .flavorType(FlavorType.버번.toString())
                .ingredientType(IngredientType.밀.toString())
                .build();
        whiskyService.updateWhisky(req,file);
        Whisky whisky=whiskyRepository.findById(req.getId()).orElse(null);
        Assertions.assertEquals(file.getOriginalFilename(),whisky.getOriImgName());

    }
    @Test
    @DisplayName("위스키 주인장 추천 등록시 수정메서드 테스트")
    public void t3(){
        Whisky tw1=whiskyRepository.save(Whisky.builder().name("테스트1").build());
        Whisky tw2=whiskyRepository.save(Whisky.builder().name("테스트2").build());
        log.info(tw1.getId()+" "+tw2.getId());
        AdminOwnerWhiskyReq req1=AdminOwnerWhiskyReq.builder()
                .id(String.valueOf(tw1.getId()))
                .build();
        AdminOwnerWhiskyReq req2=AdminOwnerWhiskyReq.builder()
                .id(String.valueOf(tw2.getId()))
                .build();

        List<AdminOwnerWhiskyReq> list=new ArrayList<>();
        list.add(req1);
        list.add(req2);

        whiskyService.updateOwnerWhisky(list);
        Whisky w1=whiskyRepository.findById(Long.parseLong(req1.getId())).orElse(null);
        Whisky w2=whiskyRepository.findById(Long.parseLong(req2.getId())).orElse(null);
        assertEquals(1L,w1.getOwnerRank());
        assertEquals(2L,w2.getOwnerRank());
    }
}