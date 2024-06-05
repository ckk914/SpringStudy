package com.study.SpringStudy.springmvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 로컬 서버에 저장된 이미지를 웹 브라우저에서 불러올 수 있도록
//웹 서버 url로 변경하는 설정
@Configuration
public class LocalResourceConfig implements WebMvcConfigurer {
    @Value("${file.upload.root-path}")
    private String rootPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*
        *   addResourceLocations: 로컬에 있는 경로
        *  addResourceHandler     : 해당 로컬 경로를 web url로 변환
        *
        *   ex)
        *   D:/xxx/dog.jpg
        *
        * local 접근  - file:D:/xxx/dog.jpg
        * web 접근 - http://localgost:8383/local/dog.jpg
        * */
        registry
                .addResourceHandler("/local/**")
        .addResourceLocations("file:"+rootPath);
    }
}
