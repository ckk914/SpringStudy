package com.study.SpringStudy.springmvc.chap03.controller;

import com.study.SpringStudy.springmvc.chap03.dto.ScoreListResponseDto;
import com.study.SpringStudy.springmvc.chap03.dto.ScorePostDto;
import com.study.SpringStudy.springmvc.chap03.entity.Score;
import com.study.SpringStudy.springmvc.chap03.repository.ScoreJdbcRepository;
import com.study.SpringStudy.springmvc.chap03.repository.ScoreJdbcRepository;
import com.study.SpringStudy.springmvc.chap03.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/*
    # 요청 URL
    1. 학생 성적정보 등록화면을 보여주고 및 성적정보 목록조회 처리
    - /score/list : GET

    2. 성적 정보 등록 처리 요청
    - /score/register : POST

    3. 성적정보 삭제 요청
    - /score/remove : POST   < 실무용    지금은 편의상 get으로 하겠음..

    4. 성적정보 상세 조회 요청
    - /score/detail : GET
 */
@Controller
@RequestMapping("/score")  //  /score/* 과 같음.
@RequiredArgsConstructor  // 필수 파라미터(final 필드) 초기화 생성자
public class ScoreController {

    // 의존객체 설정

    private final ScoreRepository repository;
//    @Autowired


    @GetMapping("/list")                    //   /score/list일 경우 여기 진행
    public String list(@RequestParam(defaultValue = "num") String sort,Model model) {  //여기 모델은 Spring 자체 모델 : 넘겨주기 위함
        System.out.println("/score/list : GET!");

        //select 전체 조회
        List<Score> scoreList = repository.findAll(sort);

        // 데이터 재가공
        List<ScoreListResponseDto> dtos = scoreList.stream()
                .map(s -> new ScoreListResponseDto(s))
                .collect(Collectors.toList());
        //jsp로 전송
        model.addAttribute("sList",dtos);  //갖다 쓸 수 있게 실음
        return "score/score-list";                                                   //jsp 호출
    }

    @PostMapping("/register")
    public String register(ScorePostDto dto) {
        System.out.println("/score/register : POST!");
        System.out.println("dto = " + dto);

        // 데이터베이스에 저장
        Score score = new Score(dto);
        repository.save(score); //insert 문 실행~!

        //다시 조회로 돌아가야 저장된 데이터를 볼 수 있음
        //포워딩이 아닌 리다이렉트로 재요청을 넣어야 새롭게 디비를 조회
        return "redirect:/score/list";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("sn") long StuNum) {
        System.out.println("/score/remove : POST!");

        repository.delete(StuNum);
        return "redirect:/score/list";
    }

    @GetMapping("/detail")
    public String detail(String stuNum,Model model) {
        System.out.println("/score/detail : GET!");
        //1. 상세 조회를 원하는 학번을 읽기
        //2. db에 상세조회 요청
        //3. db에서 조회한 회원정보 jsp에게 전달
        System.out.println("stuNum = " + stuNum);
        Score score = repository.fineOne(Long.parseLong(stuNum)); //학번을 넘겨받아서 조회
        System.out.println("score = " + score);
        int[] rankAndAll = repository.findRankByStuNum(Long.parseLong(stuNum)); //랭킹 조회
        System.out.println("나의 등수 = " + rankAndAll[0]);
        System.out.println("전체 인원 수= " + rankAndAll[1]);

        model.addAttribute("score",score);
        model.addAttribute("rank",rankAndAll[0]);
        model.addAttribute("peopleCnt",rankAndAll[1]);
        return "score/score-detail";
    }

}
