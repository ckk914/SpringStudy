package com.study.SpringStudy.webservlet.chap02.v4.Controller;

import com.study.SpringStudy.webservlet.MemberMemoryRepo;
import com.study.SpringStudy.webservlet.Model;
import com.study.SpringStudy.webservlet.entity.Member;

import java.util.Map;

public class DetailController implements ControllerV4{
    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();
    @Override
    public String process(Map<String, String> paramMap, Model model) {
        // 1. 상세 조회할 멤버의 account를 읽음
        String account = paramMap.get("account");
        //2. 저장소에서상세 조회할 멤버의 객체를 가져옴
        Member member = repo.findOne(account);
        //3. 상세 조회할 페이지(jsp)에게 멤버 정보를 보내줌
        model.addAttribute("mem",member);
        //4. 상세 조회 페이지로 이동
        return "v4/detail";
    }
}
