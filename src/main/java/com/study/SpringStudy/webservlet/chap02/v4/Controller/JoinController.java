package com.study.SpringStudy.webservlet.chap02.v4.Controller;

import com.study.SpringStudy.webservlet.Model;

import java.util.Map;

public class JoinController implements ControllerV4{
    @Override
    public String process(Map<String, String> paramMap, Model model) {
        return "v4/reg_form";
    }
}
