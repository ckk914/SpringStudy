package com.study.SpringStudy.chap03;

import com.study.SpringStudy.chap03.config.HotelManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelDITest {
    @Test
    void diTest(){
        HotelManager manager = new HotelManager();

        Hotel hotel = manager.hotel();
        hotel.inform();

    }

}