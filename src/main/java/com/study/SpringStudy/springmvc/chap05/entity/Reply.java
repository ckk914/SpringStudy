package com.study.SpringStudy.springmvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

/*
-- 댓글 테이블 생성
 -- cascade 원본 데이터 지워지면 댓글도 지워짐
 CREATE TABLE tbl_reply(
   reply_no int(8) primary key auto_increment,
   reply_text varchar(1000) not null,
   reply_writter varchar(100) not null,
   reply_date datetime default current_timestamp,
   board_no INT(8),
   constraint fk_reply
   foreign key (board_no)
   references tbl_board (board_no)
   on delete cascade
   );
* */
// Setter는 필요할때만 만들자 ⭐️
@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
    private long replyNo;                           //댓글 번호
    @Setter
    private String replyText;                   //댓글 내용
    private String replyWriter;                 //댓글 작성자
    private LocalDateTime replyDate;        //댓글 작성 시간
    private long boardNo;                           //게시물 번호




}
