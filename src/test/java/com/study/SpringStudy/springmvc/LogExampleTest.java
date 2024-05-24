package com.study.SpringStudy.springmvc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LogExampleTest {

    @Autowired
    LogExample logExample;

    @Test
    @DisplayName("")
    void logTest() {
        //given
        logExample.showLog();  //설정에 따라 보여지는게 다르다~! 보여지는 등급~! 어플리케이션.프로퍼티

        //when

        //then
    }

}