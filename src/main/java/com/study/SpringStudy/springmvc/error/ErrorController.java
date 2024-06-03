package com.study.SpringStudy.springmvc.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {          //에러 화면 연결.!
    @GetMapping("/error/404")
    public String error404(){
        return "error/error404";
    }
    @GetMapping("/error/500")
    public String error500(){
        return "error/error500";
    }
    @GetMapping("/access-deny")
    public String error500(String message, Model model){
        model.addAttribute("msg", message);
        return "error/access-deny";
    }
}
