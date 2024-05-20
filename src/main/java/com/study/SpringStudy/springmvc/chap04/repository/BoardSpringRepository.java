package com.study.SpringStudy.springmvc.chap04.repository;

import com.study.SpringStudy.springmvc.chap03.entity.Score;
import com.study.SpringStudy.springmvc.chap04.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor    //final만 골라서 주입 RequiredArgsConstructor
public class BoardSpringRepository implements BoardRepository {
    //Jdbc를 쓰기 위한 Template 선언
    private final JdbcTemplate template;

    //전체 조회
    @Override
    public List<Board> findAll() {
        String sql = "SELECT * FROM tbl_board";
        return template.query(sql, (rs, n) -> new Board(rs));  //n: rowMapper
    }
    //클릭한 게시글 개별 조회
    @Override
    public Board findOne(int boardNo) {
        String sql = "SELECT * FROM tbl_board WHERE board_no = ?";
        return template.queryForObject(sql, (rs, n) -> new Board(rs), boardNo);

    }


    @Override
    public boolean save(Board board) {
        System.out.println("저장⭐️");
        //컬럼 모음 생략하면 6개 컬럼을 다 채워야한다
        String sql = "INSERT INTO tbl_board " +
                "(title, content, Writer) " +
                "VALUES (?, ?, ?)";
        return template.update(sql,
                board.getTitle(), board.getContent(), board.getWriter()) == 1;
    }

    //삭제~!
    @Override
    public boolean delete(int boardNo) {
        String sql = "DELETE FROM tbl_board WHERE board_no = ?";
        return template.update(sql, boardNo) == 1;
    }


}
