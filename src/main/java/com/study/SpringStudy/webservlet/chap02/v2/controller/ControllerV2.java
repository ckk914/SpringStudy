package com.study.SpringStudy.webservlet.chap02.v2.controller;

import com.study.SpringStudy.webservlet.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    // 각 요청에 맞는 적절한 처리를 하고 난 후
    // 보여줄 적절한 페이지를 리턴
    View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
