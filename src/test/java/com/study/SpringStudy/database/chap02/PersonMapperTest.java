package com.study.SpringStudy.database.chap02;

import com.study.SpringStudy.database.chap01.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonMapperTest {

    @Autowired
    PersonMapper mapper;

    @Test
    @DisplayName("마이바티스 매퍼로 사람 정보를 등록한다.")
    void saveTest() {
        //given
        Person p =new Person(9999,"마바맨",59);
        Person p2 =new Person(3331,"마바22맨",29);
        //when
        boolean flag = mapper.save(p2);
        //then
        assertTrue(flag);
    }
    @Test
    @DisplayName("삭제가 된다.")
    void delete() {
        //given
        long id = 9999;
        //when
        boolean flag = mapper.delete(id);
        //then
        assertTrue(flag);
    }
    @Test
    @DisplayName("아이디가 300인 사람의 정보를 수정한다")
    void updateTest() {
        //given
        Person p = new Person(300,"뉴마바",300);
        //when
        boolean flag = mapper.update(p);
        //then
        assertTrue(flag);
    }
    
    @Test
    @DisplayName("전체 테이블을 조회하여 결과 데이터를 보여준다.")
    void findAllTest() {
        //given
        
        //when
        List<Person> people = mapper.findAll();
        //then
        people.forEach(System.out::println);

        assertEquals(2,people.size());
    }

    @Test
    @DisplayName("아이디로 사람을 조회한다.")
    void findOneTest() {
        //given
        long id = 300;
        //when
        Person p = mapper.findOne(id);
        //then
        System.out.println("p = " + p);
        assertEquals("뉴마바",p.getPersonName());
    }
@Test
@DisplayName("사람수와 이름 리스트를 조회")
void findNamesTest() {
    //given
    List<String> names = mapper.findNames();
    int count = mapper.count();

    //when
    names.forEach(System.out::println);
    System.out.println();
    System.out.println("count = " + count);

    //then
}
}