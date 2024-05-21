package com.study.SpringStudy.springmvc.chap04.service;

import com.study.SpringStudy.springmvc.chap03.dto.ScoreListResponseDto;
import com.study.SpringStudy.springmvc.chap03.entity.Score;
import com.study.SpringStudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.SpringStudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.SpringStudy.springmvc.chap04.entity.Board;
import com.study.SpringStudy.springmvc.chap04.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper mapper;


  //목록 조최 요청 중간 처리
    public List<BoardListResponseDto> findAll() {
        //BoardListResponseDto::new

        //조회해온 게시물 리스트에서 각 게시물들의 조회수를 확인하여
        //조회수가 5이상인 게시물에 특정 마킹
         return mapper.findAll().stream().map(BoardListResponseDto::new)
                .collect(Collectors.toList());
    }

    //등록 요청 중간 처리
    public void save(Board board) {
        mapper.save(board);
    }
    //삭제 요청 중간 처리
    public void delete(int boardNum) {
        mapper.delete(boardNum);
    }

    //상세 조회 요청 중간 처리

    /**
     *
     * @param BoardDetailResponseDto = 커스텀 객체
     * @return
     */
    public BoardDetailResponseDto findOne(int boardNo) {
        return new BoardDetailResponseDto(mapper.findOne(boardNo));
    }

    public void upViewCount(int boardNo) {
        mapper.upViewCount(boardNo);
    }

    public List<BoardListResponseDto> addListWork(List<Board> boardList) {
        List<BoardListResponseDto> bList = new ArrayList<>();
        for (Board b : boardList) {
            BoardListResponseDto dto = new BoardListResponseDto(b);
            bList.add(dto);
        }
        return bList;
    }
}
