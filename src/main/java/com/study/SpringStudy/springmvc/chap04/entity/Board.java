package com.study.SpringStudy.springmvc.chap04.entity;

import com.study.SpringStudy.springmvc.chap03.entity.Grade;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    private int boardNo;                               // 게시글 번호
    private String title;                               //글제목
    private String content;                         //글내용
    private String writer;                          //작성자명
    private int viewCount;                          // 조회수
    private LocalDateTime regDateTime; //작성 일시

    //생성자
    public Board(ResultSet rs) throws SQLException {
        this.boardNo = rs.getInt("board_no");
        this.title = rs.getString("title");
        this.content = rs.getString("content");
        this.writer = rs.getString("writer");
        this.viewCount = rs.getInt("view_count");
        this.regDateTime = rs.getTimestamp("reg_date_time").toLocalDateTime();


    }
}
