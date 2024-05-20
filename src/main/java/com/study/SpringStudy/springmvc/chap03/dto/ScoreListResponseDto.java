package com.study.SpringStudy.springmvc.chap03.dto;

import com.study.SpringStudy.springmvc.chap03.entity.Score;
import lombok.Getter;

@Getter
public class ScoreListResponseDto {
    private long stuNum;
    private String maskingName;  //첫글자 빼고 모두 * 처리
    private double Average;
    private String grade;

    public ScoreListResponseDto(Score s) {
        this.stuNum = s.getStuNum();
        this.maskingName =makeMaskingName(s.getStuName());
        this.Average = s.getAverage();
        this.grade  =  s.getGrade().toString();

    }

    private String makeMaskingName(String stuName) {
        char firstLetter = stuName.charAt(0);
        String maskName = "" + firstLetter; //빈문자랑 더하면 String 처리됨
        for (int i = 0; i <stuName.length() - 1  ; i++) {
            maskName+="*";
        }
        return maskName;
    }
}
