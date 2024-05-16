package com.study.SpringStudy.springmvc.chap03.repository;

import com.study.SpringStudy.springmvc.chap03.entity.Score;

import java.util.List;

// 역할: 적당한 저장소에 CRUD하기
public interface ScoreRepository {

    // 저장소에 데이터 추가하기
    boolean save(Score score);

    // 저장소에서 데이터 전체조회하기
  List<Score> findAll();
    // 저장소에서 데이터 개별조회하기
    Score fineOne(long stuNum);
    int[] findRankByStuNum(long stuNum);
    // 저장소에서 데이터 삭제하기

}
