package com.study.SpringStudy.springmvc.chap05.mapper;

import com.study.SpringStudy.springmvc.chap05.entity.ViewLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ViewLogMapper {
    //조회수 기록 추가 (insert)
    void insertViewLog(ViewLog viewLog);
    //조회수 기록 시간 추정
    void updateViewLog(ViewLog viewLog);
    //조회수 기록 조회
    ViewLog findOne(@Param("account") String account,
                      @Param("bno") long bno);
}
