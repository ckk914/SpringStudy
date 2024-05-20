package com.study.SpringStudy.springmvc.chap04.controller;

import com.study.SpringStudy.springmvc.chap03.dto.ScorePostDto;
import com.study.SpringStudy.springmvc.chap03.entity.Score;
import com.study.SpringStudy.springmvc.chap03.repository.ScoreRepository;
import com.study.SpringStudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.SpringStudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.SpringStudy.springmvc.chap04.dto.BoardPostDto;
import com.study.SpringStudy.springmvc.chap04.entity.Board;
import com.study.SpringStudy.springmvc.chap04.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor  // 필수 파라미터(final 필드) 초기화 생성자
public class BoardController {

    private final BoardRepository repository;

    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(Model model) {
        //1. 데이터 베이스로 부터 게시글 목록 조회
        List<Board> boardList = repository.findAll();
        System.out.println("boardList = " + boardList);
        System.out.println("======");
        //2. 클라이언트에 데이터 보내기 전에 렌더링 필요한
        //   데이터 추출하기
        List<BoardListResponseDto> bList = new ArrayList<>();
        for (Board b : boardList) {
            BoardListResponseDto dto = new BoardListResponseDto(b);
            bList.add(dto);
        }
//        List<BoardListResponseDto> bList = boardList.stream()
//                        .map(BoardListResponseDto::new)
        System.out.println("⭐️boardList = " + boardList);

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
        repository.save(board); //insert 문 실행~!
        return "redirect:/board/list";
    }

    //4. 게시글 삭제 요청(/board/remove : GET)
    //-> 목록 조회 요청 리다이렉션
    @GetMapping("/delete")
    public String delete(@RequestParam("bn") int boardNum) {
        System.out.println("/board/delete : POST!");
        System.out.println("@boardNum = " + boardNum);
        repository.delete(boardNum);
        return "redirect:/board/list";
    }

    //5. 게시글 상세 조회 요청 (/board/detail :get)
    @GetMapping("/detail")
    public String detail(@RequestParam("bno") String boardNo, Model model) {
        System.out.println("boardNo = " + boardNo);
        //상세 조회하고 싶은 글번호 읽기

        //2. 데이터베이스로부터 해당 글번호 데이터 조회하기
        Board board = repository.findOne(Integer.parseInt(boardNo)); //학번을 넘겨받아서 조회
        System.out.println("board = " + board);
        //3. jsp 파일에 조회한 데이터 보내기
        model.addAttribute("board", new BoardDetailResponseDto(board));
        return "/board/detail";
    }
}
