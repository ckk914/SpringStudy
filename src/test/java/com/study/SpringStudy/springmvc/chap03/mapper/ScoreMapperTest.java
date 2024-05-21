package com.study.SpringStudy.springmvc.chap03.mapper;

import com.study.SpringStudy.springmvc.chap03.dto.RankDto;
import com.study.SpringStudy.springmvc.chap03.dto.ScoreDetailResponseDto;
import com.study.SpringStudy.springmvc.chap03.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreMapperTest {

    @Autowired
    ScoreMapper mapper;

    @Test
    @DisplayName("전체 조회")
    void findAllTest() {
        //given
        List<Score> scoreList  = mapper.findAll(null);
        //when
        scoreList.forEach(System.out::println);
        //then
    }

    @Test
    @DisplayName("개별 조회")
    void findOneTest() {
        //given
        long stuNum = 11;
        Score score = mapper.findOne(stuNum);
        //when
        System.out.println("score = " + score);
        //then
    }

    @Test
    @DisplayName("랭킹 조회")
    void  rankTest() {
        //given
        long stuNum = 11;
        //when
        RankDto rankByStuNum = mapper.findRankByStuNum(stuNum);
        //then
        System.out.println("rankByStuNum = " + rankByStuNum);
    }

}