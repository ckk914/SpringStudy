package com.study.SpringStudy.springmvc.chap05.rest;

import com.study.SpringStudy.database.chap01.Person;
import com.study.SpringStudy.springmvc.chap04.common.PageMaker;
import com.study.SpringStudy.springmvc.chap04.common.Search;
import com.study.SpringStudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.SpringStudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.study.SpringStudy.springmvc.chap04.common.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/rest")
//@Controller
//@ResponseBody
// Controller + ResponseBody = RestController
@RestController
@RequiredArgsConstructor
public class RestApiController {

    private final BoardService boardService;

    //RestApi = api  요청에 응답하는 것
    @GetMapping("/hello")
    @ResponseBody //서버가 클라이언트에게 순수한 데이터를 전달할때 사용⭐️
    public String hello() {
        //...

        return "안녕아녕 메롱메롱";  //데이터를 순수하게 넘김 !  클라이언트(폰, 테블릿 등등)가 다양해서 그냥 넘김~!
    }

    @GetMapping("/hobby")
//    @ResponseBody  클래스에 붙이면 한번만 붙이면 됨 (주석하고 위에 붙였음!)
    public List<String> hobby() {
        //배열에 담아보내기
        //<-자바에서 잭슨이 알아서 변환해서 보낸다 contentType =>application/json
        List<String> hobbies = List.of("태권도", "장기", "댄스");


        return hobbies;
    }


    //    @GetMapping(value = "/person", produces = "application/json")  //기본값⭐️
    //      ll
    @GetMapping("/person")
//    @ResponseBody
    public Person person() {
        Person p = new Person(100, "핑핑이", 10);

        return p;
    }

    @GetMapping("/board")
    public Map<String, Object> board() {

        List<BoardListResponseDto> list = boardService.findList(new Search());

        HashMap<String, Object> map = new HashMap<>();
        map.put("articles", list);
        map.put("page", new PageMaker(new Page(),
                boardService.getCount(new Search())));

        return map;
    }
    /*
         RestController에서 리턴타입을 ResponseEntity를 쓰는 이유

         - 클라이언트에게 응답할 때는 메시지 바디안에 들어 있는 데이터도 중요하지만
         - 상태코드와 헤더정보를 포함해야 한다 ⭐ ️
         - 근데 일반 리턴타입은 상태코드와 헤더정보를 전송하기 어렵다
     */

    @GetMapping("/people")
    public ResponseEntity<?> people(){
        Person p1 = new Person(111, "딸기맨", 30);
        Person p2 = new Person(222, "딸기맨2", 31);
        Person p3 = new Person(333, "딸기맨3", 32);

        List<Object> people = List.of(p1, p2, p3);
        HttpHeaders headers = new HttpHeaders();
        headers.add("mypet","catcat");
        headers.add("money","100");

        //응답 코드 전송 > 직접 조작할 수 있다 원하는 번호로~!
        // 200 ok 400 클라이언트가 잘못된 데이터 전송
        // 404 url 찾지 못함 403 유료결제 안해서 안됨 500 서버 잘못
        return ResponseEntity
                .ok()   //ok200
                .headers(headers)
                .body(people); //주고싶은 데이터
    }

    @GetMapping("/bmi")
    public ResponseEntity<?> bmi(
            //@RequestParam(required = false) 입력 안해도 에러 안나게 하기 위함
            @RequestParam(required = false) Double cm,
            @RequestParam(required = false) Double kg
    ){
        //널 검사
        if (cm == null || kg == null) {
            return ResponseEntity
                    .badRequest()
                    .body("키와 몸무게를 전달하세요!");
        }
        double m= cm /100;
        double bmi = kg / (m*m);

        return ResponseEntity
                .ok()
                .body(bmi);
    }

}
