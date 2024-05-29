package com.study.SpringStudy.springmvc.chap05.mapper;

import com.study.SpringStudy.springmvc.chap05.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    //회원 가입
    boolean save(Member member);
    //회원 정보 개별 조회
    Member findOne(String account);
    //중복 확인 DB( 아이디, 이메일 )

    /*
     *
     * @param type - 어떤걸 중복 검사할지 (Ex: account Or email)
     * @param keyword - 중복 검사할 실제값
     * @return - 중복이면 true, 아니면 false
     */
    boolean existsById(@Param("type") String type, @Param("keyword") String keyword);
}
