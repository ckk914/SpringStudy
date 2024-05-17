package com.study.SpringStudy.springmvc.chap03.repository;

import com.study.SpringStudy.springmvc.chap03.entity.Score;

import java.util.List;

// 역할: 적당한 저장소에 CRUD하기
public interface ScoreRepository {

    // 저장소에 데이터 추가하기
    boolean save(Score score);

    // 저장소에서 데이터 전체조회하기
  List<Score> findAll(String sort);
    // 저장소에서 데이터 개별조회하기
    Score fineOne(long stuNum);
    //랭킹 조회  전체 인원수 + 랭킹
    int[] findRankByStuNum(long stuNum);
    // 저장소에서 데이터 삭제하기
    //defult 추가 하면 인터페이스 다 추가안하고 여기서만 쓸 수 있음~!⭐️
//    default boolean delete(long stuNum){
    default boolean delete(long stuNum){
        System.out.println("d?");
        return false;
    }

}
