package com.study.SpringStudy.springmvc.chap05.mapper;

import com.study.SpringStudy.springmvc.chap05.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    @DisplayName("회원가입에 성공해야한다")
    void saveTest() {
        //given
        Member member = Member.builder()
                .account("kuromi")
                .password("abc1234!")
                .name("쿠로미")
                .email("kuromi@gmail.com")
                .build();

        //when
        boolean flag = memberMapper.save(member);
        //then
        assertTrue(flag);

    }

    //
    @Test
    @DisplayName("회원을 개별 조회해야한다")
    void findOneTest() {
        //given
        String account = "kuromi";
        //when
        Member m = memberMapper.findOne(account);
        System.out.println("m = " + m);
        assertEquals("쿠로미",m.getName());
        //then
    }

    //
    @Test
    @DisplayName("가입 검사")
    void exitstsByIdTest() {
        String name = "kuromi";
        //given
        boolean flag = memberMapper.existsById("account",name);

      if(flag) System.out.println("가입됨");
      else System.out.println("노가입");
        //when

        //then
    }
}