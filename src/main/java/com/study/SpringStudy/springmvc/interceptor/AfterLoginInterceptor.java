package com.study.SpringStudy.springmvc.interceptor;

import com.study.SpringStudy.springmvc.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//로그인한 회원은 회원가입 페이지와 로그인 페이지에 접근을 차단
@Configuration   //스프링이 주입할 수 있도록 (=Component)
@Slf4j
public class AfterLoginInterceptor implements HandlerInterceptor {
    //클라이언트의 요청이 컨트롤러에 들어가기 전에 해야할 일을 명시
//     preHandle=문지기 역할
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("after login interceptor execute!");
        if(LoginUtil.isLoggedIn(request.getSession())) {        //로그인이 되어있으면 못들어가게
            log.info("origin:{}",request.getRequestURI());
            response.sendRedirect("/");  //리다이렉트로 홈으로 보냄
            return false;   //true일 경우, 컨트롤러 진입 허용 / false는 진입 차단
        }
        return true;
    }
}
