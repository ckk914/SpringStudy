package com.study.SpringStudy.webservlet;

import com.study.SpringStudy.webservlet.entity.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    //멤버 삭제 기능
    public void delete(String account){
        List<Member> members = memberList.stream()
                .filter(member -> member
                        .getAccount().equals(account))
                .collect(Collectors.toList());
        if(members.size() >0){
            memberList.remove(members.get(0));
        }
    }
    //멤버 단일 조회 기능
    public Member findOne(String account){
       return memberList.stream()
                .filter(member -> member
                        .getAccount().equals(account))
                .collect(Collectors.toList())
               .get(0);
    }
}
