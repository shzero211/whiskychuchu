package site.shkrr.whiskychuchu.rank.service.whisky;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.shkrr.whiskychuchu.rank.service.AwsS3ResourceService;
import site.shkrr.whiskychuchu.rank.common.util.file.MultipartUtil;
import site.shkrr.whiskychuchu.rank.domain.whisky.Whisky;
import site.shkrr.whiskychuchu.rank.service.dto.FileDetail;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class WhiskyImgService {
    private final AwsS3ResourceService awsS3ResourceService;
    /**
     * 위스키 이미지 정보 수정
     * */
    public void updateWhiskyImg(Whisky whisky, MultipartFile file) {
        //S3파일 저장에 필요한 데이터 생성
        FileDetail fileDetail=FileDetail.multipartOf(file);

        awsS3ResourceService.update(fileDetail,whisky,file);

        //새 이미지 정보로 수정
        whisky.updateImgData(fileDetail.getOriImgName(), fileDetail.getSavedName(),fileDetail.getSavedPath());
    }
    /**
     * 크롤링된 이미지의 URL 주소를 통해 이미지 가져와서 S3에 이미지 변경후 DB 정보 변경
     * */
    public void updateCrawledWhiskyImg(Whisky whisky,String imgUrlStr) throws IOException {
        MultipartFile multipartFile = MultipartUtil.createMultipartFile(whisky,imgUrlStr);

        updateWhiskyImg(whisky,multipartFile);
    }
    /**
     *  크롤링된 이미지의 URL 주소를 통해 이미지 가져와서 S3에 이미지 저장후 DB 정보 변경
     * */
    public void saveCrawledWhiskyImg(Whisky whisky,String imgUrlStr) throws IOException {
        MultipartFile multipartFile = MultipartUtil.createMultipartFile(whisky,imgUrlStr);
        FileDetail fileDetail=FileDetail.multipartOf(multipartFile);

        awsS3ResourceService.store(fileDetail,whisky,multipartFile);

        //새 이미지 정보로 수정
        whisky.updateImgData(fileDetail.getOriImgName(), fileDetail.getSavedName(),fileDetail.getSavedPath());
    }

    public void deleteWhiskyImg(Whisky whisky) {
        awsS3ResourceService.delete(whisky.getSavedName());
    }
}
