package com.study.SpringStudy.springmvc.chap03.dto;

//역할 : 브라우저가 전달한 성적 정보를 포장하는 객체
public class ScorePostDto {
    private String name;
    private int kor;
    private int eng;
    private int math;

    //Getter & Setter & toString()

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    @Override
    public String toString() {
        return "ScorePostDto{" +
                "name='" + name + '\'' +
                ", kor=" + kor +
                ", eng=" + eng +
                ", math=" + math +
                '}';
    }
}
