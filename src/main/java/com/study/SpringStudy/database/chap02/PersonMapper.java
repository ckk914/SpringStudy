package com.study.SpringStudy.database.chap02;

import com.study.SpringStudy.database.chap01.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//Mabatis 는 구현 클래스 안만들고 sql 만 만들면 된다~!⭐️
@Mapper
public interface PersonMapper {
    //사람 전체 조회
    List<Person> findAll();
    //사람 개별 조회
    Person findOne(long id);
    //사람 등록
    boolean save(Person p);
    //사람 수정
    boolean update(Person newPerson);
    //사람 삭제
    boolean delete(long id);
    //사람들의 이름만 조회
    List<String> findNames();

    //사람들의 수 조회
    int count();

}
