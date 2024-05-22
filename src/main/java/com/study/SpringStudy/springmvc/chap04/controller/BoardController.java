package com.study.SpringStudy.springmvc.chap04.controller;

import com.study.SpringStudy.springmvc.chap04.common.Page;
import com.study.SpringStudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.SpringStudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.SpringStudy.springmvc.chap04.dto.BoardPostDto;
import com.study.SpringStudy.springmvc.chap04.entity.Board;
import com.study.SpringStudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


//dto = 객체의 과한 정보를 줄여서 필요한 데이터만 사용하려고 하는 커스텀 객체

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor  // 필수 파라미터(final 필드) 초기화 생성자
public class BoardController {

//    private final BoardRepository repository;
    private final BoardService service;

    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(Page page, Model model) {
        //1. 데이터 베이스로 부터 게시글 목록 조회
        List<BoardListResponseDto> bList = service.findAll(page);
        //2. 클라이언트에 데이터 보내기 전에 렌더링 필요한
        //   데이터 추출하기
        model.addAttribute("bList", bList);
        return "board/list";

    }

    //2. 글쓰기 화면 양식 요청 (/board/write: GET)
    @GetMapping("/write")
    public String write() {

        return "board/write";
    }

    //3. 게시글 등록 요청 (/board/wite : post) : 디비로 입력
    //-> 목록 조회 요청 리다이렉션
    @PostMapping("/write")
    public String dbWrite(BoardPostDto dto) {
        System.out.println("/board/register : POST!");
        System.out.println("dto = " + dto);
//        Board board = new Board();
            Board board = dto.toEntity();
//        board.setWriter(dto.getWriter());
//        board.setContent(dto.getContent());
//        board.setTitle(dto.getTitle());

        System.out.println("board = " + board);
        // 데이터베이스에 저장
        service.save(board); //insert 문 실행~!
        return "redirect:/board/list";
    }

    //4. 게시글 삭제 요청(/board/remove : GET)
    //-> 목록 조회 요청 리다이렉션
    @GetMapping("/delete")
    public String delete(@RequestParam("bn") int boardNum) {
        System.out.println("/board/delete : POST!");
        System.out.println("@boardNum = " + boardNum);
        service.delete(boardNum);
        return "redirect:/board/list";
    }

    //5. 게시글 상세 조회 요청 (/board/detail :get) + 조회수 올라가는 업데이트 요청~!
    @GetMapping("/detail")
    public String detail(@RequestParam("bno") int boardNo, Model model) {
        System.out.println("boardNo = " + boardNo);
        //상세 조회하고 싶은 글번호 읽기

        //2. 데이터베이스로부터 해당 글번호 데이터 조회하기
        BoardDetailResponseDto board = service.findOne(boardNo); //학번을 넘겨받아서 조회
        //조회에 성공했다면, 조회수 상승을 한다
        if(board != null) service.upViewCount(boardNo);
        System.out.println("board = " + board);
        //3. jsp 파일에 조회한 데이터 보내기
        model.addAttribute("board", board);
        return "/board/detail";
    }
}
