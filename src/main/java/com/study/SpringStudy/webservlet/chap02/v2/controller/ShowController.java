package com.study.SpringStudy.webservlet.chap02.v2.controller;

import com.study.SpringStudy.webservlet.MemberMemoryRepo;
import com.study.SpringStudy.webservlet.View;
import com.study.SpringStudy.webservlet.chap02.v1.controller.ControllerV1;
import com.study.SpringStudy.webservlet.entity.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowController implements ControllerV2 {

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    @Override
    public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> memberList = repo.findAll();
        request.setAttribute("memberList", memberList);

        return new View("v2/m-list");
    }
}
