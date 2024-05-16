package com.study.SpringStudy.webservlet.chap02.v5;

import com.study.SpringStudy.webservlet.MemberMemoryRepo;
import com.study.SpringStudy.webservlet.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chap02/v5/*")
public class MemberController {

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    // 회원등록폼을 열어주는 요청
    @RequestMapping("/join")
    public String join() {
        return "v5/reg_form";
    }

    // 회원 저장을 하는 요청
    @RequestMapping("/save")
    public String save(Member member) {
        repo.save(member);
        return "redirect:/chap02/v5/show";
    }

    // 회원 목록을 조회하는 요청
    @RequestMapping("/show")
    public String show(Model model) {
        List<Member> memberList = repo.findAll();
        model.addAttribute("memberList", memberList);
        return "v5/m-list";
    }
    //회원 삭제 처리
    @RequestMapping("/delete")
    public  String delete(String account){
        repo.delete(account);
        return "redirect:/chap02/v5/show";
    }

    // 회원 개별조회 처리
    @RequestMapping("/detail")
    public String detail(String account, Model model) {
        Member member = repo.findOne(account);
        model.addAttribute("mem", member);
        return "v5/detail";
    }

}
