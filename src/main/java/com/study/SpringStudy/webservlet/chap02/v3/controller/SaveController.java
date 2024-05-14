package com.study.SpringStudy.webservlet.chap02.v3.controller;

import com.study.SpringStudy.webservlet.MemberMemoryRepo;
import com.study.SpringStudy.webservlet.ModelAndView;
import com.study.SpringStudy.webservlet.View;
import com.study.SpringStudy.webservlet.entity.Member;

import java.util.Map;

public class SaveController implements ControllerV3{
    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();
    @Override
    public ModelAndView process(Map<String, String> paramMap) {
        String userName = paramMap.get("account");
        String account = paramMap.get("password");
        String password = paramMap.get("userName");

        Member member = new Member(account, password, userName);
        repo.save(member);

//        response.sendRedirect("/chap02/v1/show");
        return new ModelAndView("redirect:/chap02/v2/show");
    }
}
