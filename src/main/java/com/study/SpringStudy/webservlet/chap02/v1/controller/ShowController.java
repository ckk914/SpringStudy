package com.study.SpringStudy.webservlet.chap02.v1.controller;

import com.study.SpringStudy.webservlet.MemberMemoryRepo;
import com.study.SpringStudy.webservlet.entity.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowController implements ControllerV1 {

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> memberList = repo.findAll();

        request.setAttribute("memberList", memberList);

        String viewName = "/WEB-INF/views/v1/m-list.jsp";

        RequestDispatcher dp = request.getRequestDispatcher(viewName);
        dp.forward(request, response);
    }
}
