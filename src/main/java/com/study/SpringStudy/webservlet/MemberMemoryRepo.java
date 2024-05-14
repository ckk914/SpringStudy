package com.study.SpringStudy.webservlet;

import com.study.SpringStudy.webservlet.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberMemoryRepo {

    public MemberMemoryRepo() {
    }
    private static MemberMemoryRepo repo  = new MemberMemoryRepo();

    public static MemberMemoryRepo getInstance(){
        return repo;
    }

    //필드
    private List<Member> memberList  = new ArrayList<>();

    //멤버 저장 기능 저장
    public void save(Member member){
        memberList.add(member);
//        System.out.println("memberList = " + memberList);
    }
    //멤버 전체 조회 기능
    public List<Member> findAll() {
        return memberList;
    }
}
