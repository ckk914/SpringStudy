package com.study.SpringStudy.springmvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hw/*")
public class LoginController {

    /*
        1번요청: 로그인 폼 화면 열어주기
        - 요청 URL : /hw/s-login-form : GET
        - 포워딩 jsp파일 경로:  /WEB-INF/views/chap03/s-form.jsp
        - html form: 아이디랑 비번을 입력받으세요.
*/
    //주소 창에 치는 자체가 그냥 겟이다 겟 get⭐️
    //GET 요청만 받겠다
    @GetMapping("/s-login-form")
    public String login() {

        return "mvc/s-form";
    }

    /*
            2번요청: 로그인 검증하기
            - 로그인 검증: 아이디를 grape111이라고 쓰고
                                     비번을 ggg9999 라고 쓰면 성공

            - 요청 URL : /hw/s-login-check : POST
            - 포워딩 jsp파일 경로:  /WEB-INF/views/chap03/s-result.jsp
            - jsp에게 전달할 데이터: 로그인 성공여부, 아이디 없는경우, 비번 틀린경우
         */
    @PostMapping("/s-login-check")
    public String loginCheck(
            String id,
            String pw,
            Model model) {

        System.out.println("id = " + id);
        System.out.println("pw = " + pw);

        //읽은 데이터 검증 진행~!
        String message = "";
        if (id.equals("grape111")) {
            if (pw.equals("ggg999")) {
                //success
//                model.addAttribute("result", "success");
                message = "success";
            } else {
                //pw 틀림
//                model.addAttribute("result", "f-pw");
                message="f-pw";
            }
        } else {
            //id 없음
//            model.addAttribute("result", "f-id");
            message="f-id";
        }

        model.addAttribute("result", message);

        return "mvc/s-result";

    }
}
