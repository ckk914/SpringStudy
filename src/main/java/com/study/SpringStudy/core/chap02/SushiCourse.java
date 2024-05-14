package com.study.SpringStudy.core.chap02;

public class SushiCourse implements Course{
    public void combineMenu(){
        System.out.println("====== 스시 코스 구성 ======");
        System.out.println("1. 대합 맑은국");
        System.out.println("2. 전어, 고등어, 도미 스시");
        System.out.println("3. 구이 요리");
        System.out.println("4. 오도로, 우니군함, 복어");
        System.out.println("5. 튀김 요리");
        System.out.println("6. 아나고덮밥");
        System.out.println("7. 조리장 특선 디저트");
    }
}
