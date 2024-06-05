package com.study.SpringStudy.springmvc.chap05.dto.request;

import com.study.SpringStudy.springmvc.chap05.entity.Member;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//회원가입할때 사용할 객체
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SignUpDto {
    @NotBlank(message = "아이디는 필수값입니다.")
    @Size(min=4,max=14, message="아이디는 4~14글자")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자만 포함해야 합니다.")
    private String account;

    @NotBlank
    private String password;
    @NotBlank
    @Size(min=2, max=6)
    private String name;

    @NotBlank
    @Email
    private String email;

    //프로필 사진 데이터 // 여러장이면 List로 읽기
    private MultipartFile profileImage;

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
