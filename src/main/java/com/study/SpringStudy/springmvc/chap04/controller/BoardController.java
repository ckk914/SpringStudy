package com.study.SpringStudy.springmvc.chap04.controller;

import com.study.SpringStudy.springmvc.chap03.dto.ScorePostDto;
import com.study.SpringStudy.springmvc.chap03.entity.Score;
import com.study.SpringStudy.springmvc.chap03.repository.ScoreRepository;
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

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor  // 필수 파라미터(final 필드) 초기화 생성자
public class BoardController {

    private final BoardRepository repository;
    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(Model model){
        List<Board> boardList =repository.findAll();
        System.out.println("boardList = " + boardList);
        System.out.println("======");
        for(Board b:boardList) {
            System.out.println(b.getTitle().length());
            if(b.getTitle().length()>10){
                String s = b.getTitle().substring(0,10);
                s+= "...";
                b.setTitle(s);
            }
            if(b.getContent().length()>15){
                String s2 = b.getContent().substring(0, 13);
                s2+="...";
                b.setContent(s2);
            }
        }
        System.out.println("⭐️boardList = " + boardList);

        model.addAttribute("bList",boardList);
        return "board/list";

    }

    //2. 글쓰기 화면 양식 요청 (/board/write: GET)
@GetMapping("/write")
    public String write(){

        return "board/write";
}
    //3. 게시글 등록 요청 (/board/wite : post) : 디비로 입력
    //-> 목록 조회 요청 리다이렉션
    @PostMapping("/write")
    public String dbWrite(BoardPostDto dto){
            System.out.println("/board/register : POST!");
            System.out.println("dto = " + dto);
            Board board = new Board();

            board.setWriter(dto.getWriter());
            board.setContent(dto.getContent());
            board.setTitle(dto.getTitle());
        System.out.println("board = " + board);
            // 데이터베이스에 저장
           repository.save(board); //insert 문 실행~!
        return "redirect:/board/list";
    }

    //4. 게시글 삭제 요청(/board/remove : GET)
    //-> 목록 조회 요청 리다이렉션
    @GetMapping("/delete")
    public String delete(@RequestParam("bn")int boardNum){
            System.out.println("/board/delete : POST!");
        System.out.println("@boardNum = " + boardNum);
        repository.delete(boardNum);
        return "redirect:/board/list";
    }
    //5. 게시글 상세 조회 요청 (/board/detail :get)
    @GetMapping("/detail")
    public String detail(@RequestParam("bno")String boardNo,Model model){
        System.out.println("boardNo = " + boardNo);
        Board board = repository.findOne(Integer.parseInt(boardNo)); //학번을 넘겨받아서 조회
        System.out.println("board = " + board);


        model.addAttribute("board",board);
        return "/board/detail";
    }
}
