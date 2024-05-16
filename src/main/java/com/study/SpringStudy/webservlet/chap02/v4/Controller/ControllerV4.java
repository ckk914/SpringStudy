package com.study.SpringStudy.webservlet.chap02.v4.Controller;

import com.study.SpringStudy.webservlet.Model;

import java.util.Map;

public interface ControllerV4 {
    /**
     * 요청이 들어오면 적절한 처리를 수행
     *
     * @param paramMap : 요청 정보 (쿼리 파라미터) 를 모두 읽어서 맵에 담음
     * @return - 응답시 포워딩하거나 리다이렉트할 경로 문자열만 주면 너가 열어라⭐️v4
     * @param2 model      :  응답 시 보여줄 jsp에 보낼 데이터를 담는 수송 객체
     */
//    View process(Map<String,String> paramMap);
    String process(Map<String,String> paramMap, Model model);
}
