package com.study.SpringStudy.webservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModelAndView {

    private View view;   // 화면 처리
    private MyModel myModel; // 화면에 필요한 데이터 처리

    public ModelAndView(String viewName) {
        this.view = new View(viewName);
        this.myModel = new MyModel();
    }

    // 화면 렌더링 기능 (포워딩 or 리다이렉트)
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.view.render(request, response);
    }

    // 모델에 JSP가 필요한 데이터를 담는 메서드
    public void addAttribute(String key, Object value) {
        this.myModel.addAttribute(key, value);
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public MyModel getModel() {
        return myModel;
    }

    public void setModel(MyModel myModel) {
        this.myModel = myModel;
    }
}
