package site.shkrr.whiskychuchu.app.global.util.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
@Slf4j
public class MultipartUtil {

    /**
     * 새로운 파일 고유 ID를 생성합니다.
     * @return 36자리의 UUID
     */
    public static String createUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     */
    public static String getExtension(String contentType) {
        if (StringUtils.hasText(contentType)) {
            return contentType.substring(contentType.lastIndexOf('/')+1 );
        }
        return null;
    }

    /**
     * 파일의 전체 경로를 생성합니다.
     */
    public static String createSavedName(String uuid, String extension) {
        return String.format("%s.%s",uuid, extension);
    }
    //이미지 URL 로 MultipartFile 생성
    public static MultipartFile createMultipartFile(Whisky whisky, String imgUrlStr) throws IOException {
        BufferedImage originalImage = null;
        originalImage = ImageIO.read(new URL(imgUrlStr));//URL 로 이미지 읽기
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( originalImage, "jpg", baos );//이미지 ByteArray 로 변경
        baos.flush();
        String name=null;
        if(whisky.getOriImgName().equals("empty")){
            name=String.format("%s.%s",whisky.getName(),"jpg");
        }else{
            name=whisky.getOriImgName();
        }

        return new MockMultipartFile(name,name,"image/jpg",baos.toByteArray());//MultipartFile 로 변경
    }
}
