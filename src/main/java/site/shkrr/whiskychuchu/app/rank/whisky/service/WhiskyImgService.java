package site.shkrr.whiskychuchu.app.rank.whisky.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class WhiskyImgService {

    @Value("${file.dir}") //위스키 이미지 파일 경로(yml 에서 불러옴)
    private String fileDir;



    /**
     * 위스키 이미지 정보 수정
     * */
    public void updateWhiskyImg(Whisky whisky, MultipartFile file) {
        String oriImgName=file.getOriginalFilename();
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
            file.transferTo(new File(savedPath));
        } catch (IOException e) {
            throw new RuntimeException("file 경로가 올바르지 않습니다.");
        }
    }

    public void deleteWhiskyImg(Whisky whisky) {
        File file=new File(whisky.getSavedPath());
        file.delete();
    }
    /**
     * 크롤링된 이미지의 URL 주소를 통해 이미지 가져와서 DB에  이미지 정보 저장및 이미지 공간에 저장
     * */
    public void crawledWhiskyImgUpdate(Whisky whisky,String imgUrlStr) {
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
    }
}
