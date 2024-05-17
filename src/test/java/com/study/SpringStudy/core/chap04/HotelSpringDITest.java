package com.study.SpringStudy.core.chap04;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class HotelSpringDITest {
    @Test
    void diTest(){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HotelConfig.class);

        //Bean : 콩 (=자바 객체)
        //Component 넘겨 받은 객체들을 알아서 골라서 객체를 만들어준다,~!
        //스프링 = 객체 보관 창고 느낌
        Hotel hotel = context.getBean(Hotel.class);
        //System.out.println("hotel = " + hotel);
               hotel.inform();

    }

}