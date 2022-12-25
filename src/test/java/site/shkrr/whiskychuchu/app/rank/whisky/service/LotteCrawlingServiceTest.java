package site.shkrr.whiskychuchu.app.rank.whisky.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;
import site.shkrr.whiskychuchu.app.rank.whisky.repository.WhiskyRepository;
import site.shkrr.whiskychuchu.app.rank.whisky.service.CrawlingService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@SpringBootTest
@Transactional
class LotteCrawlingServiceTest {
    @Autowired
    private CrawlingService crawlingService;

    @Autowired
    private WhiskyRepository whiskyRepository;

    @Value("${file.dir}") //위스키 이미지 파일 경로(yml 에서 불러옴)
    private String fileDir;

    @Test
    public void t1(){
        crawlingService.crawling();
    }

    @Test
    @DisplayName("크롤링 URL 이미지 정보 제대로 추춮 되는지 확인 테스트")
    @Disabled
    public void t2(){
        String imgUrlStr="https://contents.lotteon.com/itemimage/_v031652/LM/50/10/10/61/13/12/7_/00/1/LM5010106113127_001_1.jpg/dims/optimize/dims/resizemc/360x360";
        Whisky whisky=whiskyRepository.findById(1L).orElse(null);
        BufferedImage image=null;
        try {
            image= ImageIO.read(new URL(imgUrlStr));
        } catch (IOException e) {
            throw new RuntimeException("URL 경로가 이상합니다.");
        }
        String oriImgName=whisky.getOriImgName().equals("empty")?whisky.getName()+".jpg": whisky.getOriImgName();
        String uuid= UUID.randomUUID().toString();
        String extension=oriImgName.substring(oriImgName.lastIndexOf("."));
        String savedName=uuid+extension;
        String savedPath=fileDir+savedName;
        //이전 이미지 파일 삭세
        File oldFile=new File(whisky.getSavedPath());
        oldFile.delete();
        //새 이미지 정보로 수정
        whisky.updateImgData(oriImgName,savedName,savedPath);
        try {
            //새 이미지 파일 저장
            File file=new File(savedPath);
            ImageIO.write(image,extension.replace(".",""),file);
        } catch (IOException e) {
            throw new RuntimeException("file 경로가 올바르지 않습니다.");
        }
        whiskyRepository.save(whisky);
    }
    }