package com.study.SpringStudy.springmvc.chap05.service;

import com.study.SpringStudy.springmvc.chap05.dto.request.AutoLoginDto;
import com.study.SpringStudy.springmvc.chap05.dto.request.LoginDto;
import com.study.SpringStudy.springmvc.chap05.dto.request.SignUpDto;
import com.study.SpringStudy.springmvc.chap05.dto.response.LoginUserInfoDto;
import com.study.SpringStudy.springmvc.chap05.entity.Member;
import com.study.SpringStudy.springmvc.chap05.mapper.MemberMapper;
import com.study.SpringStudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;

import static com.study.SpringStudy.springmvc.chap05.service.LoginResult.NO_PW;
import static com.study.SpringStudy.springmvc.chap05.service.LoginResult.SUCCESS;
import static com.study.SpringStudy.springmvc.util.LoginUtil.AUTO_LOGIN_COOKIE;
import static com.study.SpringStudy.springmvc.util.LoginUtil.LOGIN;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원 가입 중간 처리
    public boolean join(SignUpDto dto) {
        // dto를 엔터티로 변환
        Member member = dto.toEntity();

        // 비밀번호를 인코딩(암호화)
        String encodedPassword = encoder.encode(dto.getPassword());
        member.setPassword(encodedPassword);

        return memberMapper.save(member);
    }

    // 로그인 검증 처리
    public LoginResult authenticate(LoginDto dto, HttpSession session, HttpServletResponse response) {

        // 회원가입 여부 확인
        Member foundMember = memberMapper.findOne(getAccount(dto));
        if (foundMember == null) {
            log.info("{} - 회원가입이 필요합니다.", dto.getAccount());
            return LoginResult.NO_ACC;
        }
        //비밀번호 일치 검사
        String inputPassword = dto.getPassword();                       //클라이언트에 입력한 비밀번호
        String OriginPassword = foundMember.getPassword();  //데이터베이스에 저장된 비밀번호

        //passwordEncoder에서는 암호화된 비번을 내부적으로 비교해주는 기능을 제공
        if (!encoder.matches(inputPassword, OriginPassword)) {
            log.info("비밀번호가 일치하지 않습니다.");
            return NO_PW;
        }

        //자동 로그인 추가 처리
        if(dto.isAutoLogin()){
            //1. 자동 로그인 쿠키 생성
            //- 쿠키 내부에 절대로 중복되지 않는 유니크한 값을 저장
            //  (UUID, SESSION ID)
            Cookie autoLoginCookie = new Cookie(AUTO_LOGIN_COOKIE, session.getId());

            //쿠키설정
            autoLoginCookie.setPath("/"); // 사이트 어디서 쓸지 설정
            autoLoginCookie.setMaxAge(60* 60 * 24 * 90); //자동로그인 유지시간 90일
            // 2. 쿠키를 클라이언트에게 전송 - 응답 바디에 실어보내야함
            response.addCookie(autoLoginCookie);
            //3. DB에 해당 쿠키값을 저장
            memberMapper.updateAutoLogin(
                    AutoLoginDto.builder()
                            .sessionId(session.getId())
                            .account(getAccount(dto))
                            .limitTime(LocalDateTime.now().plusDays(90))
                    .build());

        }

        log.info("{}님 로그인 성공", foundMember.getName());
        //세션의 수명 : 설정된 시간 or 브라우저를 닫기 전까지
        int maxInactiveInterval = session.getMaxInactiveInterval();
        session.setMaxInactiveInterval(60 * 60); //세션 수명  1시간 설정  1당 1초
        log.info("sessionTime:{}", maxInactiveInterval); //세션 시간 log
        //세션에 저장
        session.setAttribute(LOGIN, new LoginUserInfoDto(foundMember));;
//        session.setAttribute("login", new LoginUserInfoDto(foundMember));
//        session.setAttribute("loginUserName", foundMember.getName());
        return SUCCESS;

    }

    private static String getAccount(LoginDto dto) {
        return dto.getAccount();
    }

    //아이디 이메일 중복 검사
    public boolean checkIdentifier(String type, String keyword) {
        return memberMapper.existsById(type, keyword);
    }

}

