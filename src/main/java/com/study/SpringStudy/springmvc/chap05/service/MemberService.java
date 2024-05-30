package com.study.SpringStudy.springmvc.chap05.service;

import com.study.SpringStudy.springmvc.chap05.dto.request.LoginDto;
import com.study.SpringStudy.springmvc.chap05.dto.request.SignUpDto;
import com.study.SpringStudy.springmvc.chap05.entity.Member;
import com.study.SpringStudy.springmvc.chap05.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.study.SpringStudy.springmvc.chap05.service.LoginResult.NO_PW;
import static com.study.SpringStudy.springmvc.chap05.service.LoginResult.SUCCESS;


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
    public LoginResult authenticate(LoginDto dto){

        // 회원가입 여부 확인
        Member foundMember = memberMapper.findOne(getAccount(dto));
        if(foundMember == null){
            log.info("{} - 회원가입이 필요합니다.",dto.getAccount());
            return LoginResult.NO_ACC;
        }
        //비밀번호 일치 검사
        String inputPassword = dto.getPassword();                       //클라이언트에 입력한 비밀번호
        String OriginPassword = foundMember.getPassword();  //데이터베이스에 저장된 비밀번호

        //passwordEncoder에서는 암호화된 비번을 내부적으로 비교해주는 기능을 제공
        if(!encoder.matches(inputPassword,OriginPassword)){
            log.info("비밀번호가 일치하지 않습니다.");
            return NO_PW;
        }

        log.info("{}님 로그인 성공",foundMember.getName());
        return SUCCESS;

    }

    private static String getAccount(LoginDto dto) {
        return dto.getAccount();
    }

    //아이디 이메일 중복 검사
    public boolean checkIdentifier(String type, String keyword){
        return memberMapper.existsById(type,keyword);
    }

}

