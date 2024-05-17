package com.study.SpringStudy.springmvc.chap04.repository;

import com.study.SpringStudy.springmvc.chap03.entity.Score;
import com.study.SpringStudy.springmvc.chap04.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardSpringRepository implements BoardRepository {

    private final JdbcTemplate template;

    @Override
    public List<Board> findAll() {
        String sql = "SELECT * FROM tbl_board";
        return template.query(sql, (rs, n) -> new Board(rs));  //n: rowMapper
    }

    @Override
    public Board findOne(int boardNo) {
        String sql = "SELECT * FROM tbl_board WHERE board_no = ?";
        return template.queryForObject(sql, (rs, n) -> new Board(rs), boardNo);

    }


    @Override
    public boolean save(Board board) {
        System.out.println("저장⭐️");
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
