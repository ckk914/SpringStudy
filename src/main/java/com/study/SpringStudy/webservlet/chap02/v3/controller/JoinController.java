package com.study.SpringStudy.webservlet.chap02.v3.controller;

import com.study.SpringStudy.webservlet.ModelAndView;
import com.study.SpringStudy.webservlet.View;

import java.util.Map;

public class JoinController implements ControllerV3{

    @Override
    public ModelAndView process(Map<String, String> paramMap) {
        return new ModelAndView("v3/reg_form");
    }
}
