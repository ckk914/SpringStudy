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
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;

import static com.study.SpringStudy.springmvc.chap05.service.LoginResult.*;
import static com.study.SpringStudy.springmvc.util.LoginUtil.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원 가입 중간 처리
    public boolean join(SignUpDto dto, String profilePath) {
        // dto를 엔터티로 변환
        Member member = dto.toEntity();
        member.setProfileImg(profilePath); // 프로필 사진 경로 엔터티에 설정


        // 비밀번호를 인코딩(암호화)
        String encodedPassword = encoder.encode(dto.getPassword());
        member.setPassword(encodedPassword);

        return memberMapper.save(member);
    }


    // 로그인 검증 처리
    public LoginResult authenticate(LoginDto dto, HttpSession session,
                                    HttpServletResponse response) {

        // 회원가입 여부 확인
        String account = dto.getAccount();
        Member foundMember = memberMapper.findOne(account);

        if (foundMember == null) {
            log.info("{} - 회원가입이 필요합니다.", account);
            return NO_ACC;
        }

        // 비밀번호 일치 검사
        String inputPassword = dto.getPassword(); // 클라이언트에 입력한 비번
        String originPassword = foundMember.getPassword(); // 데이터베이스에 저장된 비번

        // PasswordEncoder에서는 암호화된 비번을 내부적으로 비교해주는 기능을 제공
        if (!encoder.matches(inputPassword, originPassword)) {
            log.info("비밀번호가 일치하지 않습니다.");
            return NO_PW;
        }


        //  ## 자동로그인 추가 처리
        if (dto.isAutoLogin()) {
            // 1. 자동 로그인 쿠키 생성
            // - 쿠키 내부에 절대로 중복되지 않는 유니크한 값을 저장
            //   (UUID, SessionID)
            String sessionId = session.getId();     //브라우저 재시작 하면 변함
            Cookie autoLoginCookie =
                    new Cookie(AUTO_LOGIN_COOKIE, sessionId);
            // 쿠키 설정
            autoLoginCookie.setPath("/"); // 쿠키를 사용할 경로
            autoLoginCookie.setMaxAge(60 * 60 * 24 * 90); // 자동로그인 유지 시간

            // 2. 쿠키를 클라이언트에 전송 - 응답바디에 실어보내야 함
            // response : 응답 객체
            response.addCookie(autoLoginCookie);

            // 3. DB에도 해당 쿠키값을 저장
            memberMapper.updateAutoLogin(
                    AutoLoginDto.builder()
                            .sessionId(sessionId)
                            .limitTime(LocalDateTime.now().plusDays(90))
                            .account(account)
                            .build()
            );
        }

        maintainLoginState(session, foundMember);

        return SUCCESS;
    }

    public static void maintainLoginState(HttpSession session, Member foundMember) {
        log.info("{}님 로그인 성공", foundMember.getName());

        // 세션의 수명 : 설정된 시간 OR 브라우저를 닫기 전까지
        int maxInactiveInterval = session.getMaxInactiveInterval();
        session.setMaxInactiveInterval(60 * 60); // 세션 수명 1시간 설정
        log.debug("session time: {}", maxInactiveInterval);

        session.setAttribute(LOGIN, new LoginUserInfoDto(foundMember));
    }


    // 아이디, 이메일 중복검사
    public boolean checkIdentifier(String type, String keyword) {
        return memberMapper.existsById(type, keyword);
    }


    public void autoLoginClear(HttpServletRequest request,HttpServletResponse response) {

        // 1. 쿠키 제거하기
        Cookie c = WebUtils.getCookie(request, AUTO_LOGIN_COOKIE);
        if(c!=null){
            c.setPath("/");
            c.setMaxAge(0);  //0초로 하면 제거됨.
            response.addCookie(c);
        }
        //2. db에 자동로그인 컬럼들을 원래대로 돌림
        memberMapper.updateAutoLogin(
                AutoLoginDto.builder()
                        .sessionId("none")
                        .limitTime(LocalDateTime.now())
                        .account(LoginUtil.getLoggedInUserAccount(request.getSession()))
                        .build()
        );
    }
}
