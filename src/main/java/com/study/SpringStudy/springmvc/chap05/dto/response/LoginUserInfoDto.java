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
    private String account;
    private String nickName;
    private String email;
    private String auth;

    public LoginUserInfoDto(Member member){
        this.account = member.getAccount();
        this.email = member.getEmail();
        this.nickName = member.getName();
        this.auth = member.getAuth().name();  //.name() 쓰면 enum에서 대문자만 뜯어온다~!
    }
}
