package com.study.SpringStudy.springmvc.chap04.dto;

import com.study.SpringStudy.springmvc.chap04.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//서버에서 조회한 데이터 중 화면에 필요한 데이터만 모아놓은 클래스
@Getter
public class BoardListResponseDto {
    //필드명은 프론트엔드와 소통해서 맞춰야함 ⭐️
    private String shortTitle; //5글자 이상 줄임 처리된 제목
    private int boardNo;                //글번호
    private String shortContent;  //30자 이상 줄임 처리된 글 내용
    private String date; // 포맷팅된 날짜 문자열
    private int view;     // 조회수
    private String writer;

    //엔터티를 DTO로 변환하는 생성자

    public BoardListResponseDto(Board b) {
       this.shortTitle = makeShortTitle(b.getTitle());
       this.shortContent  =makeShortContent(b.getContent());
       this.date = dateFomatting(b.getRegDateTime());
       this.view = b.getViewCount();
       this.writer = b.getWriter();
       this.boardNo = b.getBoardNo();
    }

    private String dateFomatting(LocalDateTime regDateTime) {
        DateTimeFormatter pattern =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return pattern.format(regDateTime);
    }

    private String makeShortContent(String content) {
        return (content.length() > 30)
                ? content.substring(0, 30) + "..."
                : content;
    }

    private String makeShortTitle(String title) {
        return (title.length() > 5)? title.substring(0,5)+"..." :title ;
    }
}
