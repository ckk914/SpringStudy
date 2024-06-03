package com.study.SpringStudy.springmvc.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

//미리 만들어 놓은 에러페이지로 안내해주는 설정⭐️
@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        //에러 내용 정의
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,"/error/404");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");

        //에러 관련 더 필요 하면 추가하면 됨.
        registry.addErrorPages(errorPage404,errorPage500);
        //실질적인 화면 띄우는건 errorController⭐️
    }
}
