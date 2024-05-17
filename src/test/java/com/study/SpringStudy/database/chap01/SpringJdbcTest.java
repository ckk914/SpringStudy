package com.study.SpringStudy.database.chap01;

import com.study.SpringStudy.database.chap01.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  // ⭐️
@Transactional
@Rollback    //insert 여도 넣고 다시 취소 해버림 ⭐️
class SpringJdbcTest {
    @Autowired
    SpringJdbc springJdbc;  // 테스트 시 필드 주입~!

    //각 테스트 전에 공통으로 실행할 코드
    //다른 코드를 돌기 전에 미리 실행하고 진행하는 코⭐️
    @BeforeEach
    void bulkInsert(){
        for (int i = 0; i <10 ; i++) {
            Person p = new Person(i + 200, "테스트맨" + i, 10);
            springJdbc.save(p);
        }
    }

    //단위 테스트 프레임워크 JUnit5
    //테스트 == 단언 (Assertion)(=주장)
    @Test
    @DisplayName("사람의 정보를 입력하면 데이터베이스에 반드시 저장되어야 한다.")
    void saveTest(){
        //gwt 패턴
        //given : 테스트에 주어질 데이터
        Person p = new Person(1000,"천천이",1000);

        //when : 테스트 상황
        int result = springJdbc.save(p);
        //then ㅣ 테스트 결과 단언
        assertEquals(1,result);  //돌리면 1이 리턴된다고 단언~!

    }

    //delete
    @Test
    @DisplayName("아이디가 주어지면 해당 아이디의 사람정보가 데이터베이스로부터 삭제가 되어야 한다.")
    void deleteTest() {
        //given
        long id = 77;
        //when
        boolean flag = springJdbc.delete(id);
        //then
        assertTrue(flag); //실패면 ! 나옴
    }

    //update
    @Test
    @DisplayName("새로운 이름과 나이를 전달하면 정보가 데이터베이스에 업데이트가 된다.")
    void updateTest () {
        //given
        Person person = new Person(77,"팔팔이",8);
        //when
        boolean flag = springJdbc.update(person);

        //then
        assertTrue(flag);
    }
    
    @Test
    @DisplayName("사람 정보를 전체 조회하면 결과 건수는 2건이며, 첫번째 사람의 이름은 팔팔이다")
    void findAllTest() {
        //given
        
        //when
        List<Person> people = springJdbc.findAll();
        //then
        people.forEach(System.out::println);

        assertEquals(2,people.size());
        assertEquals("팔팔이",people.get(0).getPersonName());
    }

    @Test
    @DisplayName("사람 정보를 아이디로 단일 조회시 아이디가 77인 사람의 이름은 팔팔이 이고 나이는 8살이다.")
    void findOneTest() {
        //given
        long id = 77;
        //when
        Person person = springJdbc.findOne(id);
        //then
        System.out.println("person = " + person);

        assertNotNull(person);
        assertEquals("팔팔이", person.getPersonName());
        assertEquals(8, person.getPersonAge());
    }
}