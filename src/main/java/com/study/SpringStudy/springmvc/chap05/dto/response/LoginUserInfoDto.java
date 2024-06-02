package com.study.SpringStudy.springmvc.chap05.dto.response;

import com.study.SpringStudy.springmvc.chap05.entity.Member;
import lombok.*;

@Getter@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserInfoDto {

    //클라이언트에 보낼 정보
    private String account;     //계정명
    private String nickName;    //이름
    private String email;           //이메일
    private String auth;            //권한

    //생성자
    public LoginUserInfoDto(Member member){
        this.account = member.getAccount();
        this.email = member.getEmail();
        this.nickName = member.getName();
        this.auth = member.getAuth().name();  //.name() 쓰면 enum에서 대문자만 뜯어온다~!
    }
}
