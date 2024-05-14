package com.study.SpringStudy.webservlet.chap02.v3.controller;

import com.study.SpringStudy.webservlet.ModelAndView;
import com.study.SpringStudy.webservlet.View;

import java.util.Map;

/**
 *   이 인터페이스를 구현하는 다양한 하위 객체들이
 *   요청 정보나 응답정보를 모두가 사용하는 것이 아니기 때문에
 *   요청,응답 정보 처리를 외부로 위임.
 */
public interface ControllerV3 {
    /**
     * 요청이 들어오면 적절한 처리를 수행
     * @param paramMap : 요청 정보 (쿼리 파라미터) 를 모두 읽어서 맵에 담음
     * @return - 응답 시 보여줄 화면 처리 객체
     */
//    View process(Map<String,String> paramMap);
    ModelAndView process(Map<String,String> paramMap);

}
