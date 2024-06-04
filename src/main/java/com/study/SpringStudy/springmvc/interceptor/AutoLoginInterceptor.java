package com.study.SpringStudy.springmvc.interceptor;

import com.study.SpringStudy.springmvc.chap05.entity.Member;
import com.study.SpringStudy.springmvc.chap05.mapper.MemberMapper;
import com.study.SpringStudy.springmvc.chap05.service.MemberService;
import com.study.SpringStudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class AutoLoginInterceptor implements HandlerInterceptor {

    private final MemberMapper memberMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. 사이트에 들어오면
        //    자동 로그인 쿠키를 가지고 있는지 체크~!
//        Cookie[] cookies = request.getCookies();
//        for(Cookie c : cookies){
//            if(c.getName().equals(LoginUtil.AUTO_LOGIN_COOKIE)){  //
//
//            }
//        }
        //  ㅣㅣ
        Cookie autoLoginCookie = WebUtils.getCookie(request, LoginUtil.AUTO_LOGIN_COOKIE);
        //2. 자동 로그인 쿠키가 있고 &&  로그인이 안되어 있다면
        //   사이트 로그인 처리를 수행
        if(autoLoginCookie != null
                && !LoginUtil.isLoggedIn(request.getSession())) {
            //3, 쿠키에 들어있는 랜덤값 읽기
            String sessionId = autoLoginCookie.getValue();

            // 4. 세션 아이디로 회원 정보 조회
            Member foundMember = memberMapper.findMemberBySessionId(sessionId);

            //5. 회원이 정상 조회되었고, 자동 로그인 만료 시간 이전이라면
            //    사이트 로그인 처리(세션에 dto 세팅)를 수행
            if(foundMember  != null
                    && LocalDateTime.now().isBefore(foundMember.getLimitTime())){

                //자동 로그인 처리~! 껐다가 켜도 로그인 됨 / 빌드 다시해도 로그인 됨
                MemberService.maintainLoginState(request.getSession(), foundMember);
            }
        }
        return true;
    }
}
