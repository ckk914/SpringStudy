package com.study.SpringStudy.springmvc.interceptor;

import com.study.SpringStudy.springmvc.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Configuration
public class BoardInterceptor implements HandlerInterceptor {   //HandlerInterceptor 추가
    //preHandle을 구현하여
    //로그인을 안한 회원은 글쓰기, 글수정, 글삭제 요청을 거부할 것
    //거부하고 로그인 페이지로 리다이렉션을 할 것!


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        log.debug("after login interceptor execute!");
        if (!LoginUtil.isLoggedIn(request.getSession())) {           //로그인이 안되어있으면
            log.info("origin:{}", request.getRequestURI());
            String redirectUri = request.getRequestURI();
            response.sendRedirect("/members/sign-in?message=login-required&redirect=" + redirectUri);  //리다이렉트로 로그인 페이지로 보냄⭐️

            return false;   //true일 경우, 컨트롤러 진입 허용 / false는 진입 차단
        } else {
            // 로그인한 사용자인 경우
            if (request.getRequestURI().startsWith("/board/delete")) {
                // 삭제 요청인 경우
                String  loginUser =
                LoginUtil.getLoggedInUserAccount(session);
                System.out.println("loginUser = " + loginUser);
                log.info("deleteUrl:{}", request.getRequestURI());
//                if (!loginUser.isAdmin() && !isAuthorizedToDelete(request.getParameter("id"), loginUser)) {
//                    // 관리자가 아니고, 작성자도 아닌 경우 삭제 권한 없음
//                    response.sendRedirect("/board/list");
//                    return false;
//                }
            }
        }

        //삭제 요청이 들어오면 서버에서 한번더 관리자인지? 자기가 쓴글인지 체크

        return true;
    }
}
