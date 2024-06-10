package com.study.SpringStudy.springmvc.chap05.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class SnsLoginController {
    @Value("${sns.kakao.app-key}")
    private String appKey;
    @Value("${sns.kakao.redirect-uri}")
    private String redirectUri;
    //a링크는 무조건 get으로 들어온다
    @GetMapping("/kakao/login")
    public String kakaoLogin() {
        //카카오 서버로 인가 코드 발급 통신을 해야함
        String uri = "https://kauth.kakao.com/oauth/authorize";
        uri += "?client_id="+appKey;
        uri += "&redirect_uri="+redirectUri;
        uri += "&response_type=code";


        return "redirect:" + uri;
    }
    //인가 코드를 받는 요청
    @GetMapping("/oauth/kakao")
        public String kakaoCode(String code){
        log.info("카카오 인가 코드 발급 -{}",code);
        return "";
        }
}
