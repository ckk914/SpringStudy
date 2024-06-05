package com.study.SpringStudy.springmvc.chap05.api;

import com.study.SpringStudy.springmvc.chap04.common.Page;
import com.study.SpringStudy.springmvc.chap05.dto.request.ReplyModifyDto;
import com.study.SpringStudy.springmvc.chap05.dto.request.ReplyPostDto;
import com.study.SpringStudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.SpringStudy.springmvc.chap05.dto.response.ReplyListDto;
import com.study.SpringStudy.springmvc.chap05.entity.Reply;
import com.study.SpringStudy.springmvc.chap05.service.ReplyService;
import com.study.SpringStudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/replies")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin  //CORS 정책 허용 범위 설정   (origins = "http://localhost:5503")
public class ReplyApiController {

    private final ReplyService replyService;

    //댓글 목록 조회 요청
    // URL : /api/v1/replies/원본글번호/page/페이지번호  - GET :목록 조회
    // @PathVariable   URL 에 붙어있는 변수값을 읽는 아노테이션
    @GetMapping("/{bno}/page/{pageNo}")
    public ResponseEntity<?> list(@PathVariable long bno,
                                  @PathVariable int pageNo,
                                  HttpSession session) {    //GetMapping 과  이름 맞춰야함 ⭐️
        if (bno == 0) {
            String message = "글번호는 0이 될 수 없습니다.";
            log.warn(message);
            return ResponseEntity
                    .badRequest()
                    .body(message);
        }
        log.info("/api/v1/replies/{}: GET", bno);
        ReplyListDto replies = replyService.getReplies(bno, new Page(pageNo, 10));
        replies.setLoginUser(LoginUtil.getLoggedInUser(session)); //안했으면 널

        return ResponseEntity
                .ok()
                .body(replies);
    }

    //댓글 생성 요청
    //@RequestBody = 클라이언트가 전송한 데이터를 JSON으로 받아서 파싱
    //   ㄴ 달라고 하는 것
    @PostMapping
    public ResponseEntity<?> posts(
            @Validated @RequestBody ReplyPostDto dto   // @Validated 검증~!⭐️
            , BindingResult result //입력값 검증 결과 데이터를 갖고 있는 객체
            , HttpSession session

            ) {
        log.info("/api/vi/replies: post");
        log.debug("parameter: {}", dto);

        if (result.hasErrors()) {

            Map<String, String> errors = makeValidationMessageMap(result);

            return ResponseEntity               //에러 내보냄
                    .badRequest()
                    .body(errors);
        }
        log.debug(result.toString());  //노빠꾸 -> 데이터 그냥 들어감. 에러 직접 처리해야함


        boolean flag = replyService.register(dto, session);

        if (!flag) return ResponseEntity
                .internalServerError()
                .body("댓글 등록 실패!");

        return ResponseEntity
                .ok()
                .body(replyService.getReplies(dto.getBno(), new Page(1, 10)));
    }

    //BindingResult 에서 에러를 확인해서 사용자에게 적절한 에러를 준다~!⭐️
    //검증~!
    private Map<String, String> makeValidationMessageMap(BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        List<FieldError> fieldErrors = result.getFieldErrors();

        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return errors;

    }

    //삭제 처리 요청
    @DeleteMapping("/{rno}")
    public ResponseEntity<?> delete(@PathVariable long rno) {   //PathVariable : url 데이터 읽음~!

        ReplyListDto dtoList = replyService.remove(rno);

        return ResponseEntity
                .ok()
                .body(dtoList);
    }

    //댓글 수정 요청
    //@putMapping 이나 @PatchMapping 동시 사용 불가능  ⭐️
//    @PutMapping     // 전체 수정
//    @PatchMapping //일부 수정

    /*
     * let obj  ={
     *   age : 3
     * }
     *
     *   PUT - obj = {age : 10};
     *  PATCH - obj.age =  10;
     * */
    // ▽ put patch 동시 사용 방법.~!⭐️
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> modify(
            @Validated @RequestBody ReplyModifyDto dto
            , BindingResult result      //검증 객체
    ) {

        log.info("/api/v1/replies : PUT, PATCH");       //PUT 타입으로 넘겨주면 받는다~!
        log.debug("parameter: {}", dto);

        if (result.hasErrors()) {
            Map<String, String> errors = makeValidationMessageMap(result);

            return ResponseEntity
                    .badRequest()
                    .body(errors);
        }

        ReplyListDto replyListDto = replyService.modify(dto);

        return ResponseEntity.ok().body(replyListDto);

    }

}
