package com.study.SpringStudy.springmvc.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//아래껀 필수 녀석⭐️
@Controller
public class ResponseController {

    //JSP 파일로 포워딩할때 데이터 전달하기
    //1. 모델 model 객체 사용하기
    @RequestMapping("/hobbies")
    public String hobbies(Model model){   //Model은 스프링의 모델로 추가해야함~!⭐️
        //하단의 jsp로 파라미터들이 넘어감~!⭐️
    model.addAttribute("name","김철수");
    model.addAttribute("hobbies", List.of("축구","수영","족구"));
    model.addAttribute("major","컴퓨터공학");
        return "mvc/hobbies";
    }

    // 2. ModelAndView 객체 사용하기
    @RequestMapping("/hobbies2")
    public ModelAndView hobbies2(){ //ModelAndView도 스프링 껄로 참조 걸기⭐️
        ModelAndView mv = new ModelAndView("mvc/hobbies");
        mv.addObject("name","뽀로로");
        mv.addObject("hobbies",List.of("멍때리기","맛집가기"));
        mv.addObject("major","컴퓨터공학");

        return mv;
    }
}
