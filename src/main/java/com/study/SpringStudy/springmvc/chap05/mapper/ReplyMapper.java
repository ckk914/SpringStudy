package com.study.SpringStudy.springmvc.chap05.mapper;

import com.study.SpringStudy.springmvc.chap04.common.Page;
import com.study.SpringStudy.springmvc.chap05.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {  //여기 건들면 xml 수정 ㄱ ⭐️

    // 댓글 등록
    boolean save(Reply reply);

    // 댓글 수정
    boolean modify(Reply reply);

    // 댓글 삭제
    boolean delete(long replyNo);

    // 특정 게시물에 달린 댓글 목록 조회
    //파라미터는 한개만 써야한다⭐️  => 해결 방법 : 별칭 사용
    List<Reply> findAll(@Param("bno")long boardNo,
                                    @Param("p") Page page);

    // 총 댓글 수 조회
    int count(long boardNo);

    //댓글번호로 게시글번호 찾기~!
    long findBno(long rno);
}
