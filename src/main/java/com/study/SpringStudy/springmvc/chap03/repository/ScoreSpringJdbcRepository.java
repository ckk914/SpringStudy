package com.study.SpringStudy.springmvc.chap03.repository;

import com.study.SpringStudy.springmvc.chap03.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScoreSpringJdbcRepository implements ScoreRepository {

    private final JdbcTemplate template;

    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO tbl_score " +
                "(stu_name, kor, eng, math, total, average, grade) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return template.update(sql,
                score.getStuName(), score.getKor(), score.getEng()
                , score.getMath(), score.getTotal(), score.getAverage()
                , score.getGrade().toString()) == 1;
    }

    @Override
    public List<Score> findAll(String sort) {
        String sql = "SELECT * FROM tbl_score " + orderByStatement(sort);
        return template.query(sql, (rs, n) -> new Score(rs));
    }

//    @Override
//    public Score fineOne(long stuNum) {
//        String sql = "SELECT * FROM tbl_score WHERE stu_num = ?";
//        return template.queryForObject(sql, (rs, n) -> new Score(rs), stuNum);
//    }


    private String orderByStatement(String sort) {
        String sortSql = "ORDER BY ";
        switch (sort) {
            case "num":
                sortSql += "stu_num";
                break;
            case "name":
                sortSql += "stu_name";
                break;
            case "avg":
                sortSql += "average DESC";
                break;
        }
        return sortSql;
    }


    @Override
    public int[] findRankByStuNum(long stuNum) {
        String sql = "SELECT A.stu_num, A.rank, A.cnt" +
                " FROM (SELECT *, " +
                "           RANK() OVER (ORDER BY average DESC) AS rank, " +
                "           COUNT(*) OVER() AS cnt" +
                "       FROM tbl_score) A " +
                "WHERE A.stu_num = ?";
        return template.queryForObject(sql, (rs, n) -> new int[] {
                rs.getInt("rank"),
                rs.getInt("cnt")
        }, stuNum);
    }

    @Override
    public boolean delete(long stuNum) {
        String sql = "DELETE FROM tbl_score WHERE stu_num = ?";
        return template.update(sql, stuNum) == 1;
    }

    @Override
    public Score findOne(long stuNum) {
        String sql = "SELECT * FROM tbl_score WHERE stu_num = ?";
        return template.queryForObject(sql, (rs, n) -> new Score(rs), stuNum);
    }


    @Override
    public boolean updateScore(Score s){
        String sql = "UPDATE tbl_score " +
                "SET kor =?, eng = ?, math = ?, " +
                "total = ?, average =?, grade =? " +
                "WHERE stu_num = ?";
        return template.update(sql, s.getKor(),s.getEng(),s.getMath(),
                s.getTotal(),s.getAverage(),s.getGrade().toString(),s.getStuNum())==1;
    }
}
