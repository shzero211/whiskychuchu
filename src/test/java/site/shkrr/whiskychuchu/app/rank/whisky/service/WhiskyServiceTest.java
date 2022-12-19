package site.shkrr.whiskychuchu.app.rank.whisky.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.AdminWhisky;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.AdminWhiskyDetail;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.AdminWhiskyDetailReq;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.CountryType;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.FlavorType;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.IngredientType;
import site.shkrr.whiskychuchu.app.rank.whisky.repository.WhiskyRepository;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WhiskyServiceTest {
    @Autowired
    private WhiskyService whiskyService;
    @Autowired
    private WhiskyRepository whiskyRepository;

    @Autowired
    ResourceLoader loader;

    @Test
    @Rollback(value = false)
    public void t1(){
    whiskyService.crawlingAndSave();
    }

    @Test
    @DisplayName("위스키 엔티티 수정시 URL 이미지 정보로 이미지 수정할수 있는지 테스트")
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
}