package com.study.SpringStudy.springmvc.chap05.dto.request;

import lombok.*;

@Setter @Getter@ToString        //setter 안붙이면 null들어옴
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {   //jsp의 태그의 name과 하단 변수명 일치하게 만들기 ⭐️
                                            //비동기로 json이 날라오는게 아니기 때문에
                                            //json 방식으로 날라오는게 아니라서 RequestBody 안붙임!
    private String account;
    private String password;
    private boolean autoLogin; //자동 로그인 체크 여부

}
