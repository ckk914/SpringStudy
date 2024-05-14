package com.study.SpringStudy.core.chap02;

public class WesternRestaurant implements Restaurant{
    //담당 쉐프
    private JannChef chef = new JannChef();

    //요리 코스
    private FrenchCourse course = new FrenchCourse();

    //주문 기능
    public  void order(){
        System.out.println("서양 요리를 주문합니다.");
        course.combineMenu();
        chef.cook();
    }

}
