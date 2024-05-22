package com.study.SpringStudy.springmvc.chap04.mapper;

import com.study.SpringStudy.springmvc.chap04.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardMapperTest {
    @Autowired
    BoardMapper mapper;

    @Test
    @DisplayName("게시물에 300개 글 추가")
    void insertTest() {
        //given
        for (int i = 0; i <300 ; i++) {
            Board b = new Board();
            b.setTitle("테스트제목"+i);
            b.setWriter("테스트사람"+i);
            b.setContent("내용내용"+i);

            mapper.save(b);


        }
        //when

        //then
    }

}