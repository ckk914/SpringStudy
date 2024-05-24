package com.study.SpringStudy.springmvc.chap05.dto.request;

import lombok.*;

//댓글 등록 시 클라이언트에게 받는 데이터
@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyPostDto {

    private String text;       //댓글 내용
    private String author; //작성자
    private Long bno;       //원본 글번호

}
