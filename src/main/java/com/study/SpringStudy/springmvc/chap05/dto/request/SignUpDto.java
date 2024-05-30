package com.study.SpringStudy.springmvc.chap05.dto.request;

import com.study.SpringStudy.springmvc.chap05.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//회원가입할때 사용할 객체
@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SignUpDto {
    @NotBlank
    @Size(min=4,max=14)
    private String account;

    @NotBlank
    private String password;
    @NotBlank
    @Size(min=2, max=6)
    private String name;

    @NotBlank
    @Email
    private String email;

    public Member toEntity() {
//        new Member(
//        this.account,
//        this.password,
//        this.email,
//        this.name
//                  ll
        return Member.builder()
                .account(this.account)
                .password(this.password)
                .email(this.email)
                .name(this.name)
                .build();
    }
}
