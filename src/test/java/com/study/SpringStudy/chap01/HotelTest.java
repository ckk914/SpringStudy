package com.study.SpringStudy.chap01;

import com.study.SpringStudy.core.chap01.Hotel;
import org.junit.jupiter.api.Test;

class HotelTest {

    @Test
    void hotelTest(){
        Hotel hotel = new Hotel();
        hotel.inform();
    }

}