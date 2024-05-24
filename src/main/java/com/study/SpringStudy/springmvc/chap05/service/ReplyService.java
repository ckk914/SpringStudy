package com.study.SpringStudy.springmvc.chap05.service;

import com.study.SpringStudy.springmvc.chap05.dto.request.ReplyPostDto;
import com.study.SpringStudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.SpringStudy.springmvc.chap05.entity.Reply;
import com.study.SpringStudy.springmvc.chap05.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor  //생성자 생성
@Slf4j
public class ReplyService {
    private final ReplyMapper replyMapper;

    //댓글 목록 전체 조회
    public List<ReplyDetailDto> getReplies(long boardNo) {
        List<Reply> replies = replyMapper.findAll(boardNo);

        return replies.stream()
                .map(r -> new ReplyDetailDto(r))
                .collect(Collectors.toList());
    }


    //댓글 입력
    public boolean register(ReplyPostDto dto) {
        Reply reply = Reply.builder()
                .replyText(dto.getText())
                .replyWriter(dto.getAuthor())
                .boardNo(dto.getBno())
                .build();


        boolean flag = replyMapper.save(reply);
        if(flag) log.info("댓글 등록 성공!! - {}",dto);
        else log.warn("댓글 등록 실패");

        return flag;
    }

    //댓글 수정
    public void modify() {

    }

    //댓글 삭제
    public void remove() {

    }

}
