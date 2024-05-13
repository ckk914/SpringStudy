package com.study.SpringStudy.chap03.config;

import com.study.SpringStudy.chap03.*;

//객체 생성의 제어권을 모두 가지고 온 객체
public class HotelManager {
      // 쉐프 객체 생성
    public Chef chef1() {
        return new JannChef();
    }
    public Chef chef2() {
        return new JannChef();
    }
    // 요리 코스객체 생성
    public Course course1() {
        return new FrenchCourse();
    }
    public Course course2() {
        return new SushiCourse();
    }
    // 레스토랑 객체 생성
    public Restaurant restaurant1() {
        return new WesternRestaurant(chef1(), course1());
    }
    public Restaurant restaurant2() {
        return new AsianRestaurant(chef2(), course2());
    }
    // 호텔 객체 생성
    public Hotel hotel() {
        return new Hotel(restaurant2(), chef2());
    }

}
