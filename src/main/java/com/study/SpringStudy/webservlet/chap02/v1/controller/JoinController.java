package com.study.SpringStudy.webservlet.chap02.v1.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinController implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String viewName = "/WEB-INF/views/v1/reg_form.jsp";

        RequestDispatcher dp
                = request.getRequestDispatcher(viewName);
        dp.forward(request, response);

    }
}
