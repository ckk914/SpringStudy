package com.study.SpringStudy.springmvc.interceptor;

import com.study.SpringStudy.springmvc.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
@Slf4j
public class ApiAuthInterceptor implements HandlerInterceptor {

    // 비동기 통신시 인가 처리
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();  //로그인 확인 할라고 가져옴

        if (!LoginUtil.isLoggedIn(session)) {
            //로그인하지 않은 경우 403 상태 코드를 전송
            log.info("인가되지 않은 접근입니다.: {}", request.getRequestURI());
            response.sendError(403);  //403 = HttpServletResponse.SC_FORBIDDEN
            return false;  //차단
        }
        return true;
    }
}