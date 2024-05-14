package com.study.SpringStudy.webservlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// JSP파일을 포워딩하는 역할
public class View {

    private String viewName; // 포워딩할 뷰의 경로
    private String prefix; // 경로에 있는 공통 접두사
    private String suffix; // 경로에 있는 공통 접미사
    private boolean redirect; // 리다이렉트 여부

    // viewName에 register를 전달하면
    // 완성된 이름은 /WEB-INF/views/register.jsp
    // redirect일 때는 앞에있는 redirect: 을 제거
    public View(String viewName) {
        this.prefix = "/WEB-INF/views/";
        this.suffix = ".jsp";

        if (!isRedirect(viewName)) {
            this.viewName = prefix + viewName + suffix;
        } else {
            // redirect:/chap02/v2/show
            this.viewName = viewName.substring(viewName.indexOf(":") + 1);
        }
    }

    // 포워딩 or 리다이렉트 기능
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //리다이렉트가 아니면(save가 아니면)
        if (!this.redirect) {
            RequestDispatcher dp = request.getRequestDispatcher(viewName);
            dp.forward(request, response);
        } else {
            response.sendRedirect(viewName);
        }
    }
    // 리다이렉트인지 확인
    private boolean isRedirect(String viewName) {
        this.redirect = viewName.startsWith("redirect:");
        return this.redirect;
    }

}
