package com.study.SpringStudy.webservlet.chap02.v2.controller;

import com.study.SpringStudy.webservlet.View;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinController implements ControllerV2 {

    @Override
    public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new View("v2/reg_form");
    }
}
