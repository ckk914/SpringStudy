package com.study.SpringStudy.springmvc.chap03.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class ScoreModifyRequestDto {

    private long stuNum;
    private int kor, eng, math;

}
