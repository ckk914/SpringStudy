package com.study.SpringStudy.core.chap01;

public class AsianRestaurant {
    private KimuraChef chef = new KimuraChef();
    private SushiCourse course = new SushiCourse();

    public void orderMenu(){
        System.out.println("아시안 요리를 주문합니다.");
        course.combineCourse();
        chef.cooking();
    }


}
