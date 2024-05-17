package com.study.SpringStudy.springmvc.chap04.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//포장용 객체
@Getter @Setter @ToString
public class BoardPostDto {
    private String title;
    private String Content;
    private String Writer;

}
