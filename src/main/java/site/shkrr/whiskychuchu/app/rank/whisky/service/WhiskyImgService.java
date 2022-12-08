package site.shkrr.whiskychuchu.app.rank.whisky.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class WhiskyImgService {
    //위스키 이미지 파일 경로(yml 에서 불러옴)
    @Value("${file.dir}")
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

}
