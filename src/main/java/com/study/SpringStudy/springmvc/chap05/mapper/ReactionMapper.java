package com.study.SpringStudy.springmvc.chap05.mapper;

import com.study.SpringStudy.springmvc.chap05.entity.Reaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReactionMapper {
    //리액션 생성 - 좋아요/ 싫어요 처음 찍었을때
    void save(Reaction reaction);

    //리액션 삭제 - 좋아요/싫어요 취소했을때
    void delete(@Param("boardNo")long boardNo, @Param("account") String account);

    //리액션 단일 조회 - 사용자가 특정 게시물에 리액션을 했는지 확인
    Reaction findOne(@Param("boardNo")long boardNo, @Param("account") String account);

    //특정 게시물에 총 좋아요 수 조회
    int countLikes(long boardNo);
    //특정 게시물에 총 싫어요 수 조회
    int countDislikes(long boardNo);

}
