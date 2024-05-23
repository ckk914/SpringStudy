package com.study.SpringStudy.springmvc.chap04.dto;

import com.study.SpringStudy.springmvc.chap04.entity.Board;
import com.study.SpringStudy.springmvc.chap05.entity.Reply;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class BoardDetailResponseDto {
    private int boardNo;
    private String Writer;
    private String title;
    private String content;
    private String regDateTime;
    @Setter private List<Reply> replies;        //메퍼한테 받은 것을 리스트로 만들어서 보낸다

    public BoardDetailResponseDto(Board b) {
    this.boardNo = b.getBoardNo();
    this.title = b.getTitle();
    this.Writer = b.getWriter();
    this.content = b.getContent();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh시 mm분 ss초");
        this.regDateTime = pattern.format(b.getRegDateTime());

    }
}
