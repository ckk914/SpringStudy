package com.study.SpringStudy.springmvc.config;

import com.study.SpringStudy.springmvc.interceptor.AfterLoginInterceptor;
import com.study.SpringStudy.springmvc.interceptor.AutoLoginInterceptor;
import com.study.SpringStudy.springmvc.interceptor.BoardInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 만들어 놓은 인터셉터들을 스프링 컨텍스트에 등록하는 설정 파일
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    //객체 주입
    private final AfterLoginInterceptor afterLoginInterceptor;
    private final BoardInterceptor boardInterceptor;
    private final AutoLoginInterceptor autoLoginInterceptor;

    //설정 메서드
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry
                .addInterceptor(afterLoginInterceptor)              //로그인 관련 처리 (afterLoginInterceptor)
                //해당 인터셉터가 동작할 URL을 설정
                .addPathPatterns("/members/sign-up","/members/sign-in");

        //preHandle을 구현하여
        //로그인을 안한 회원은 글쓰기, 글수정, 글삭제 요청을 거부할 것
        //거부하고 로그인 페이지로 리다이렉션을 할 것!
        // 게시판 인터셉터 등록
        registry
                .addInterceptor(boardInterceptor)
                .addPathPatterns("/board/*")                                                //전체 거부
                .excludePathPatterns("/board/list", "/board/detail")        //두개는 허용
        ;

        //자동 로그인 인터셉터 등록
        registry
                .addInterceptor(autoLoginInterceptor)
                .addPathPatterns("/**");
    }
}
