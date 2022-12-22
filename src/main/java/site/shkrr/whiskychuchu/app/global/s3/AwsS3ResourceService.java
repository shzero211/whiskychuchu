package site.shkrr.whiskychuchu.app.global.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import site.shkrr.whiskychuchu.app.global.util.file.MultipartUtil;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;
import site.shkrr.whiskychuchu.app.global.util.file.dto.FileDetail;

import java.io.File;
import java.io.IOException;
@Slf4j
@Component
@RequiredArgsConstructor
public class AwsS3ResourceService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${file.dir}")
    private String fileDir;

    private final AmazonS3Client amazonS3Client;

    public void store(FileDetail fileDetail,Whisky whisky, MultipartFile multipartFile){
        //임시파일 경로 생성
        File dummyFile=new File(fileDir,fileDetail.getSavedName());

        //S3에 이미지 저장
        try {
            //S3 저장하기전 임시 파일을 생성해 줘야함
            multipartFile.transferTo(dummyFile);
            //저장
            amazonS3Client.putObject(new PutObjectRequest(bucket,fileDetail.getSavedName(), dummyFile)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new RuntimeException("file 경로가 올바르지 않습니다.");
        }finally {
            if(dummyFile.exists()){
                dummyFile.delete();//임시 파일 삭제
            }
        }
        String savedPath=amazonS3Client.getUrl(bucket,fileDetail.getSavedName()).toString();//이미지 저장 경로
        fileDetail.setSavedPath(savedPath);//DB에 저장할 이미지 저장 경로 정보 입력
    }
    public void update(FileDetail fileDetail,Whisky whisky, MultipartFile multipartFile){
        //S3에서 이미지 삭제
       delete(whisky.getSavedName());
       store(fileDetail,whisky,multipartFile);
    }
    public void delete(String savedName){
        amazonS3Client.deleteObject(bucket,savedName);
    }
}
