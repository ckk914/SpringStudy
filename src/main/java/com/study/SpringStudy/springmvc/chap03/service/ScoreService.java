package com.study.SpringStudy.springmvc.chap03.service;

import com.study.SpringStudy.springmvc.chap03.dto.*;
import com.study.SpringStudy.springmvc.chap03.entity.Score;
import com.study.SpringStudy.springmvc.chap03.mapper.ScoreMapper;
import com.study.SpringStudy.springmvc.chap03.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *   역할 : 컨트롤러와 레퍼지토리 사이에 위치하여
 *            중간 처리를 담당
 *
 *            - 트랜잭션 처리, 데이터 가공 처리 ....
 *
 *            -의존 관계
 *                  Controller -> Service -> Repository
 *
 */
@RequiredArgsConstructor
@Service   // = Component인데 자주 사용해서 Service로 명명되어있음 Spring 자체적
public class ScoreService {
    //Spring JDBC -> Mybatis
//    private final ScoreRepository repository;
    private final ScoreMapper repository;


    //목록 조회 중간 처리
    // - DB에서 조회한 성적 정보 목록은 민감한 정보를 모두 포함하고 있는데
    //   이를 컨트롤러에게 직접 넘기면 보안상 불필요한 정보까지 화면으로
    //   넘어갈 수 있기 때문에 숨길 건 숨기고 뺄건 빼는 데이터 가공을 처리한다.
    public List<ScoreListResponseDto> getList(String sort){
        List<Score> scoreList = repository.findAll(sort);
        return scoreList.stream()
                .map(s-> new ScoreListResponseDto(s))
                .collect(Collectors.toList());
    }

    //저장 중간 처리
    public boolean insert(ScorePostDto dto){
        return repository.save(new Score(dto));
    }
    //삭제 중간 처리
    public boolean deleteScore(long stuNum){
        return repository.delete(stuNum);
    }

    // 개별조회 중간처리
//    public Score retrieve(long stuNum) {
//        return repository.findOne(stuNum);
//    }

    public ScoreDetailResponseDto retrieve(long stuNum) {

        Score score = repository.findOne(stuNum);
        RankDto result = repository.findRankByStuNum(stuNum);

        ScoreDetailResponseDto dto
                = new ScoreDetailResponseDto(score, result.getRank(), result.getCnt());

        return dto;
    }

    // 수정 완료를 위해서 서비스 클래스에서
    // dto를 entity로 변환
    public void update(ScoreModifyRequestDto dto) {
        repository.updateScore(new Score(dto));
    }
}
