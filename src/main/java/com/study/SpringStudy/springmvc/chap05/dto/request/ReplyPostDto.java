package com.study.SpringStudy.springmvc.chap05.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//댓글 등록 시 클라이언트에게 받는 데이터  ( 클라이언트  > > 서버 )
@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyPostDto {                 //클라이언트가 주는 데이터 확인 검증하기 위한 곳 ⭐️ : 변수 맞춰주는 위치
    /*
    NotNull : null은 허용 안됨                           (널 x)
    NotEmpty : null은 되는데 빈문자는 안됨     (널 o 빈문자 x)
    NotBlank : null도 안되고 빈문자도 안됨       (널 x 빈문자 x)
    * */
    @NotBlank
    @Size(min = 1,max=300)
    private String text;       //댓글 내용
    @NotBlank
    @Size(min = 2,max=8)
    private String author; //작성자
    @NotNull
    private Long bno;       //원본 글번호

}
