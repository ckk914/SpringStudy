package com.study.SpringStudy.springmvc;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping("/index")
    public void home(){}
}
