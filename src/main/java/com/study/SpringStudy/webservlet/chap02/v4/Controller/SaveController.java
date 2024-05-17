package com.study.SpringStudy.webservlet.chap02.v4.Controller;

import com.study.SpringStudy.webservlet.MemberMemoryRepo;
import com.study.SpringStudy.webservlet.MyModel;
import com.study.SpringStudy.webservlet.entity.Member;

import java.util.Map;

public class SaveController implements ControllerV4{
    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    @Override
    public String process(Map<String, String> paramMap, MyModel myModel) {
        String account = paramMap.get("account");
        String password = paramMap.get("password");
        String userName = paramMap.get("userName");

        Member member = new Member(account, password, userName);

        repo.save(member);

        return "redirect:/chap02/v4/show";

    }
}
