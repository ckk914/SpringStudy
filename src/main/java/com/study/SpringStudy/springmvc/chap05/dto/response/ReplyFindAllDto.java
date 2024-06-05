package com.study.SpringStudy.springmvc.chap05.dto.response;

import lombok.*;

import java.time.LocalDateTime;
// Setter는 필요할때만 만들자 ⭐️
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyFindAllDto {
    private long replyNo;                           //댓글 번호
    @Setter
    private String replyText;                   //댓글 내용
    private String replyWriter;                 //댓글 작성자
    private LocalDateTime replyDate;        //댓글 작성 시간
    private long boardNo;                           //게시물 번호
    private String account;                         // new
    private String profileImg;                    //이미지 경로
}
