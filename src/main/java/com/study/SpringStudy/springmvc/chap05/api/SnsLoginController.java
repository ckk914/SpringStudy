package com.study.SpringStudy.springmvc.chap05.api;

import com.study.SpringStudy.springmvc.chap05.service.SnsLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@Slf4j
@RequiredArgsConstructor  //final 붙은 것만
public class SnsLoginController {
    //서비스 주입 받기
    private final SnsLoginService snsLoginService;
    //따로 설정 파일에서 읽어오기~!
    @Value("${sns.kakao.app-key}")
    private String appKey;
    @Value("${sns.kakao.redirect-uri}")
    private String redirectUri;

    //a링크 클릭 시에는 무조건 get으로 들어온다⭐️
    @GetMapping("/kakao/login")
    public String kakaoLogin() {
        //카카오 서버로 인가 코드 발급 통신을 해야함
        String uri = "https://kauth.kakao.com/oauth/authorize";
        uri += "?client_id="+appKey;
        uri += "&redirect_uri="+redirectUri;
        uri += "&response_type=code";

        //카카오 문서상 : GET 이어서 ridirect: 로 보낸다~!⭐️
        // https://kauth.kakao.com/oauth/authorize
        return "redirect:" + uri;
    }
    //인가 코드를 받는 요청(kakaoLogin→인가코드 받기)
    @GetMapping("/oauth/kakao")
        public String kakaoCode(String code, HttpSession session){
        log.info("카카오 인가 코드 발급 -{}",code);
        //토큰 발급에 필요한 파라미터 만들기
        //데이터 담아서 넘겨 줄 HashMap 생성⭐️
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("appKey",appKey);
        requestParams.put("redirect",redirectUri);
        requestParams.put("code",code);

        //인증 액세스 토큰 발급 요청(서비스로 인가)⭐️
        snsLoginService.kakaoLogin(requestParams,session);

        return "redirect:/";
        }



}
