package com.study.SpringStudy.springmvc.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

//spring 의 frontController 처럼 구현됨~!
//RequestMapping을 통해 주소 접근을 처리한다~!⭐️
// 그 아래 함수는 String 타입으로 한다~!⭐️
@Controller
@RequestMapping("/spring/chap01/*")
public class BasicController {
    //  디테일한 요청
    // url : /spring/chap01/hello
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello 요청이 들어옴~!");
        // /WEB-INF/views/hello.jsp
        //resources/application.properties 에서 적었던
        //spring.mvc.view.prefix=/WEB-INF/views/
        // 로 인해 경로의 hello가 열리는 것이다~!~!~!⭐️
        return "hello";  //열고 싶은 jsp 적기~!⭐️
    }
    //========요청 파라미터 읽기 ===========//
    //                   ㄴ 쿼리 스트링 Query String
    //                          ㄴURL에 붙어있거나 form태그에서 전송된 데이터

    //요청 파라미터 읽기 방법 1 ⭐️
    //1. HttpServletRequest 사용
    //http://localhost:8383/spring/chap01/person?name=song&age=99
    @RequestMapping("/person")
    public String person(HttpServletRequest request){
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("/person~!");
        return "";
    }
    //2. @RequestParam 사용하기
    // /spring/chap01/major?stu=kim&major=business&grade=3
    @RequestMapping("/major")
    public String major(
            @RequestParam String stu,      // 아래 특수 경우 아니면 @RequestParam 생략 가⭐️
            @RequestParam("major") String mj,  //major로 들어오는 변수를 mj로 받겠다⭐️
            @RequestParam int grade){  //파라미터 형 그냥 적으면 알아서 형변환도 다함~!⭐️

        String major ="ddd";
        System.out.println("stu = " + stu);
        System.out.println("major = " + major);
        System.out.println("grade = " + grade);

        return "";
    }

    //3. 커맨드 객체 (RequestDTO)  사용해서 파라미터 값 읽기⭐️⭐️
    // ex> /spring/chap01/order?orderNum=22&goods=구두&amount=3&price=20000....

    // http://localhost:8383/spring/chap01/order?orderNum=22&goods=aircon&amount=2&price=100000000

    @RequestMapping("/order")
    public  String order(OrderDto order){
        System.out.println("order주문번호 = " + order.getOrderNum());
        System.out.println("order주문상품명 = " + order.getGoods());
        System.out.println("order주문개수 = " + order.getAmount());
        System.out.println("order주문가격 = " + order.getPrice());
        return "";
    }
}
