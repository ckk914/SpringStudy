package com.study.SpringStudy.chap03;

import com.study.SpringStudy.core.chap03.Hotel;
import com.study.SpringStudy.core.chap03.config.HotelManager;
import org.junit.jupiter.api.Test;

class HotelDITest {
    @Test
    void diTest(){
        HotelManager manager = new HotelManager();

        Hotel hotel = manager.hotel();
        hotel.inform();

    }

}