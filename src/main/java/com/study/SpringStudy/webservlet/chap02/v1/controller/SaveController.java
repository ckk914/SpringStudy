package com.study.SpringStudy.webservlet.chap02.v1.controller;

import com.study.SpringStudy.webservlet.MemberMemoryRepo;
import com.study.SpringStudy.webservlet.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveController implements ControllerV1 {

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        Member member = new Member(account, password, userName);
        repo.save(member);

        response.sendRedirect("/chap02/v1/show");


    }
}
