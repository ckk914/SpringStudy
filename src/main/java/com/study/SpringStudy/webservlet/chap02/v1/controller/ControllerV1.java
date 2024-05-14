package com.study.SpringStudy.webservlet.chap02.v1.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {

    // 각 요청에 맞는 적절한 처리를 하는 기능
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
