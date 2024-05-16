package com.study.SpringStudy.springmvc.chap03.repository;

import com.study.SpringStudy.springmvc.chap03.entity.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreJdbcRepository implements ScoreRepository {

    private String url = "jdbc:mariadb://localhost:3306/spring5";
    private String username = "root";
    private String password = "mariadb";

    public ScoreJdbcRepository() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean save(Score score) {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "INSERT INTO tbl_score " +
                    "(stu_name, kor, eng, math, total, average, grade) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, score.getStuName());
            pstmt.setInt(2, score.getKor());
            pstmt.setInt(3, score.getEng());
            pstmt.setInt(4, score.getMath());
            pstmt.setInt(5, score.getTotal());
            pstmt.setDouble(6, score.getAverage());
            pstmt.setString(7, score.getGrade().toString());

            int result = pstmt.executeUpdate();

            if (result == 1) return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    List<Score> scoreList = new ArrayList<>();
    @Override
    public List<Score> findAll() {
        scoreList= new ArrayList<>();
        try (Connection conn = connect()) {
            String sql = "SELECT * FROM TBL_SCORE";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //select문만 ResultSet을 써서 표를 받는다~!⭐️
            ResultSet rs = pstmt.executeQuery();
            System.out.println("rs = " + rs);

            while(rs.next()){
                Score s = new Score(rs);
                scoreList.add(s);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreList;

    }
    //개별 조회 가져오기
    @Override
    public Score fineOne(long stuNum) {
        //알 수 없는 문제 대비
        if(scoreList==null || scoreList.equals("")){
            scoreList= findAll();
        }
        //리스트 있는 채로 하기 때문에 가능
        return scoreList.stream()
                .filter(score -> score.getStuNum() == stuNum)
                .collect(Collectors.toList())
                .get(0);

//아래껀 디비 사용 버전
//        try (Connection conn = connect()) {
//
//            String sql = "SELECT * FROM tbl_score WHERE stu_num = ?";
//
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setLong(1, stuNum);
//
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                return new Score(rs);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
    }
    //석차와 랭킹 순위 반환~!
    @Override
    public int[] findRankByStuNum(long stuNum) {

        try (Connection conn = connect()) {

            String sql = "SELECT A.stu_num, A.rank, A.cnt" +
                    " FROM (SELECT *, " +
                    "           RANK() OVER (ORDER BY average DESC) AS rank, " +
                    "           COUNT(*) OVER() AS cnt" +
                    "       FROM tbl_score) A " +
                    "WHERE A.stu_num = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, stuNum);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new int[] {
                        rs.getInt("rank"),
                        rs.getInt("cnt")
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    private Connection connect() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }
}
