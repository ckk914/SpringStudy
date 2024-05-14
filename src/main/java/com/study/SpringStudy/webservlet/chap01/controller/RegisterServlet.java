package com.study.SpringStudy.webservlet.chap01.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/chap01/join")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewName = "/WEB-INF/views/reg_form.jsp";

        RequestDispatcher dp
                = req.getRequestDispatcher(viewName);
        dp.forward(req, resp);
    }
}
