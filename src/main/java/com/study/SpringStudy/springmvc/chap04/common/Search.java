package com.study.SpringStudy.springmvc.chap04.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//검색은 page를 상속
@Getter@Setter@ToString
@EqualsAndHashCode
public class Search extends Page{
    //페이지 번호, 페이지 양도 상속됨.

    //검색어, 검색 조건
    private String keyword, type;

    //기본 검색은 ""으로 함 안하면 아무것도 안나옴⭐️
    public Search(){
        this.keyword = "";
    }




}
