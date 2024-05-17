package com.study.SpringStudy.core.chap04;

/*
 * @Solution
 * - 객체 생성의 제어권을 이 클래스에서
 *     다른 클래스로 이전한다.
 *    호텔 객체 생성 시 반드시 의존 객체를 전달하도록 강요
 *    (생성자를 통해서 )
 *    new 생성자();  => 이 문법을 담당 클래스를 정해서 몰아서 수행시킴 ! = 스프링 컨테이너
 *
 * // 제어의 역전(IoC) : 객체 생성의 제어권을 외부로 넘긴다.
    // 의존성 주입(DI) : 외부에서 생성된 객체를 주입받는 개념
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//여기 안에 테스트 만들었음.!⭐️
@Component   // Hotel의 객체 생성 제어권을 스프링에게 넘김~!⭐️
public class Hotel {

    //레스토랑
   // @Autowired   //자동으로 객체를 주입해줘 (필드 주입⭐️)
    private final Restaurant restaurant;  //중간에 set으로 바꾸지 못하게 final 처리 ⭐️
    //헤드쉐프
    //@Autowired   //자동으로 객체를 주입해줘
    private final Chef Chef;

//만약에 해당 클래스의 생성자가 단 한개뿐이라면 자동으로 @Autowired를 붙임
    @Autowired
//    public Hotel(@Qualifier("wwww") Restaurant restaurant, com.study.SpringStudy.core.chap04.Chef chef) {
    public Hotel(@Qualifier("wwww") Restaurant restaurant, com.study.SpringStudy.core.chap04.Chef chef) {
        this.restaurant = restaurant;
        this.Chef = chef;
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }
//    @Autowired   //수정자 주입 (Setter Injection) ⭐️
//    public void setRestaurant(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }

    public Chef getChef() {
        return Chef;
    }

//    public void setChef(Chef chef) {
//        Chef = chef;
//    }

    //호텔을 소개하는 기능
    public void inform() {
        System.out.printf("우리 호텔의 레스토랑은 %s입니다." +
                        " 그리고 헤드쉐프는 %s입니다.\n"
                , restaurant.getClass().getSimpleName()
                , Chef.getClass().getSimpleName());

        restaurant.order();
        Chef.cook();
    }
}
