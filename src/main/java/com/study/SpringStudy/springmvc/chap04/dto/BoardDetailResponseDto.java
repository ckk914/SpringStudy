package com.study.SpringStudy.springmvc.chap04.dto;

import com.study.SpringStudy.springmvc.chap04.entity.Board;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class BoardDetailResponseDto {
    private int boardNo;
    private String Writer;
    private String title;
    private String content;
    private String regDateTime;

    public BoardDetailResponseDto(Board b) {
    this.boardNo = b.getBoardNo();
    this.title = b.getTitle();
    this.Writer = b.getWriter();
    this.content = b.getContent();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh시 mm분 ss초");
        this.regDateTime = pattern.format(b.getRegDateTime());

    }
}
