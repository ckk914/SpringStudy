package com.study.SpringStudy.webservlet.chap02.v4.Controller;

import com.study.SpringStudy.webservlet.MemberMemoryRepo;
import com.study.SpringStudy.webservlet.MyModel;
import com.study.SpringStudy.webservlet.entity.Member;

import java.util.List;
import java.util.Map;

public class ShowController implements ControllerV4{
    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();
    @Override
    public String process(Map<String, String> paramMap, MyModel myModel) {
        List<Member> memberList = repo.findAll();
        //모델로 바꿈~!
        myModel.addAttribute("memberList", memberList);

        return "v4/m-list";
    }
}
