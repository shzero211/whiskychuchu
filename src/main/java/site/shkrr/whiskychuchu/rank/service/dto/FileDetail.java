package site.shkrr.whiskychuchu.rank.service.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import site.shkrr.whiskychuchu.rank.common.util.file.MultipartUtil;
/**
 *
 * MultipartFile 에서 S3파일처리에 필요한 정보를가져와 가공하는 클래스
 * */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FileDetail {
    private String oriImgName;
    private String uuid;
    private String extension;
    private String savedName;
    private String savedPath;

    public static FileDetail multipartOf(MultipartFile file){
        String uuid=MultipartUtil.createUUID();
        String extension=MultipartUtil.getExtension(file.getContentType());
        return FileDetail.builder()
                .oriImgName(file.getOriginalFilename())
                .uuid(uuid)
                .extension(extension)
                .savedName(MultipartUtil.createSavedName(uuid,extension))
                .build();
    }
}
