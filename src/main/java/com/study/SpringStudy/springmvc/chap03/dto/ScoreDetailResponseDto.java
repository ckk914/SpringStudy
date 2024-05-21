package com.study.SpringStudy.springmvc.chap03.dto;

import com.study.SpringStudy.springmvc.chap03.entity.Score;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ScoreDetailResponseDto {
    //상세 정보 화면을 렌더링하기 위한 데이터
    private long stuNum;
    private String stuName;
    private int kor,eng,math,total;
    private double average;
    private String grade;

    private int rank, totalCount;  //석차와 총학생수

    public ScoreDetailResponseDto(Score s, int rank, int count){
        this.stuNum = s.getStuNum();
        this.stuName = s.getStuName();
        this.kor = s.getKor();
        this.eng = s.getEng();
        this.math = s.getMath();
        this.total  = s.getTotal();
        this.average = s.getAverage();
        this.grade = s.getGrade().toString();
        this.rank = rank;
        this.totalCount = count;
    }
}
