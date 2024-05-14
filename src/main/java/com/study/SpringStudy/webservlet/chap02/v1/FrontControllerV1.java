package com.study.SpringStudy.webservlet.chap02.v1;

import com.study.SpringStudy.webservlet.chap02.v1.controller.ControllerV1;
import com.study.SpringStudy.webservlet.chap02.v1.controller.JoinController;
import com.study.SpringStudy.webservlet.chap02.v1.controller.SaveController;
import com.study.SpringStudy.webservlet.chap02.v1.controller.ShowController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 브라우저의 /chap02/v1/~ 시작하는 요청을 처리하는 서블릿
@WebServlet("/chap02/v1/*")
public class FrontControllerV1 extends HttpServlet {

    /*
       /chap02/v1/join  :  회원가입 화면 열기 요청
       /chap02/v1/save  :  회원정보 등록 요청
       /chap02/v1/show  :  회원리스트 조회 요청
     */

    // key: 요청 URI, value: 요청에 맞는 하위 컨트롤러 객체
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerV1() {
        controllerMap.put("/chap02/v1/join", new JoinController());
        controllerMap.put("/chap02/v1/save", new SaveController());
        controllerMap.put("/chap02/v1/show", new ShowController());
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("브라우저 요청이 들어옴!!");

        // 들어온 요청 구분하기
        String uri = req.getRequestURI();
        System.out.println("요청 uri = " + uri);

        // 요청에 맞는 적당한 컨트롤러객체를 맵에서 꺼내기
        ControllerV1 controller = controllerMap.get(uri);

        controller.process(req, resp);

    }
}
