package site.shkrr.whiskychuchu.app.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
* img src 에 예약어처럼 경로 지정
* */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${file.dir}")
    private String fileDir;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")//예약어
                .addResourceLocations("file:///"+fileDir+"/");//경로
    }
}
