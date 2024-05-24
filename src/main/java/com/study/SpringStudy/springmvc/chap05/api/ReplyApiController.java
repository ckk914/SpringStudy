package com.study.SpringStudy.springmvc.chap05.api;

import com.study.SpringStudy.springmvc.chap05.dto.request.ReplyPostDto;
import com.study.SpringStudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.SpringStudy.springmvc.chap05.entity.Reply;
import com.study.SpringStudy.springmvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/replies")
@RequiredArgsConstructor
@Slf4j
public class ReplyApiController {

    private  final ReplyService replyService;

    //댓글 목록 조회 요청
    // URL : /api/v1/replies/원본글번호 - GET :목록 조회
    // @PathVariable   URL 에 붙어있는 변수값ㅎ을 읽는 아노테이션
    @GetMapping("/{bno}")
    public ResponseEntity<?> list(@PathVariable long bno){    //GetMapping 과  이름 맞춰야함 ⭐️
        if(bno == 0){
            String message = "글번호는 0이 될 수 없습니다.";
            log.warn(message);
            return ResponseEntity
                    .badRequest()
                    .body(message);
        }
        log.info("/api/v1/replies/{}: GET",bno);
        List<ReplyDetailDto> replies = replyService.getReplies(bno);//댓글 목록 가져오기~!
        log.debug("first reply :{}", replies.get(0));
        try{

        }catch(Exception e){
            log.error("dsdfsdfsd");
        }
        return ResponseEntity
                .ok()
                .body(replies);
    }
    //댓글 생성 요청
    //@RequestBody = 클라이언트가 전송한 데이터를 JSON으로 받아서 파싱
    //   ㄴ 달라고 하는 것
    @PostMapping
    public ResponseEntity<?> posts(@RequestBody ReplyPostDto dto){
        log.info("/api/vi/replies: post");
        log.debug("parameter: {}", dto);

        boolean flag = replyService.register(dto);

        if(!flag) return ResponseEntity
                .internalServerError()
                .body("댓글 등록 실패!");

        return ResponseEntity
                .ok()
                .body(replyService.getReplies(dto.getBno()));
    }

}
