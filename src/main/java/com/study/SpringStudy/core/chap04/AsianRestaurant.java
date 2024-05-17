package com.study.SpringStudy.core.chap04;

import org.springframework.stereotype.Component;

@Component("aisan") //스프링에게 제어권을 넘김~!
public class AsianRestaurant implements Restaurant {
    private Chef chef;
    private Course course;

    public AsianRestaurant(Chef chef, Course course) {
        this.chef = chef;
        this.course = course;
    }

    public void order(){
        System.out.println("아시안 요리를 주문합니다.");
        course.combineMenu();
        chef.cook();
    }


}
