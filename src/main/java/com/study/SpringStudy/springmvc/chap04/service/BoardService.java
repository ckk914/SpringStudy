package com.study.SpringStudy.springmvc.chap04.service;

import com.study.SpringStudy.springmvc.chap04.common.Search;
import com.study.SpringStudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.SpringStudy.springmvc.chap04.dto.BoardFindAllDto;
import com.study.SpringStudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.SpringStudy.springmvc.chap04.dto.BoardWriteRequestDto;
import com.study.SpringStudy.springmvc.chap04.entity.Board;
import com.study.SpringStudy.springmvc.chap04.mapper.BoardMapper;
import com.study.SpringStudy.springmvc.chap05.mapper.ReplyMapper;
import com.study.SpringStudy.springmvc.chap05.entity.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper boardMapper;
    private final ReplyMapper replyMapper;

    // 목록 조회 요청 중간처리
    public List<BoardListResponseDto> findList(Search page) {
        List<BoardFindAllDto> boardList = boardMapper.findAll(page);

        // 조회해온 게시물 리스트에서 각 게시물들의 조회수를 확인하여
        // 조회수가 5이상인 게시물에 특정 마킹
        //dto 교체
        List<BoardListResponseDto> dtoList = boardList.stream()
                .map(b -> new BoardListResponseDto(b))
                .collect(Collectors.toList());

        return dtoList;
    }

    // 등록 요청 중간처리
    public boolean insert(BoardWriteRequestDto dto) {
        Board b = dto.toEntity();
        return boardMapper.save(b);
    }

    // 삭제 요청 중간처리
    public boolean remove(int boardNo) {
        return boardMapper.delete(boardNo);
    }

    // 상세 조회 요청 중간처리
    public BoardDetailResponseDto detail(int bno) {
        Board b = boardMapper.findOne(bno);
        if (b != null) boardMapper.upViewCount(bno);

        //아래껀 동기 방식
        // 댓글 목록 조회
//        List<Reply> replies = replyMapper.findAll(bno);
    //상세 조회 그리고 댓글은 비동기로 가져올 것임~!

        BoardDetailResponseDto responseDto = new BoardDetailResponseDto(b);
//        responseDto.setReplies(replies);

        return responseDto;
    }

    public int getCount(Search search) {
        return boardMapper.count(search);
    }
}
