package com.study.SpringStudy.springmvc.chap04.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

//페이지 화면 렌더링에 필요한 정보들을 계산
@Getter@ToString
@EqualsAndHashCode
public class PageMaker {
    //한화면에 페이지를 몇개씩 배치할 것인가??
    private  static final int PAGE_COUNT = 10;

    //페이지 시작번호와 끝번호
    private int begin, end;

    //총 게시물 수
    private int totalCount;

    //현재 페이지 정보 (몇 페이지 보고 있는지)
    private Page pageInfo;

    public PageMaker(Page page,int totalCount){
        this.pageInfo = page;
        this.totalCount = totalCount;
        makePageInfo();
    }
    //페이지 생성에 필요한 데이터를 만드는 알고리즘
    private void makePageInfo(){
        //1. end 값 계산
        /**
         *  지금 사용자가 7페이지를 보고 있다면
         *  페이지 구간ㄴ : 1~10 구간
         *
         *           *  지금 사용자가 24페이지를 보고 있다면
         *          *  페이지 구간ㄴ : 21~30 구간
         *
         *          5개씩 배치하는 경우
         *          7page : 6~10
         *         24page : 21~25
         *
         *         // 공식:
         *         (올림 (현재 사용자가 위치한 페이지넘버 / 한 화면에 보여줄 페이지 수)) * 한 화면에 보여줄 페이지 수
         */
        this.end = (int) Math.ceil((double) pageInfo.getPageNo() / PAGE_COUNT) *PAGE_COUNT;

        // 2. begin
        this.begin = this.end -PAGE_COUNT+1;

        //3. 마지막 페이지 구간에서 End 값 보정
        //  51페이지까지이면 , 1페이지만 나와야함
        /*
          총 게시물이 237개이고 한 화면에 게시물이 10개씩 배치하고 있다면

          !~10 페이지 구간 : 게시물이 총 100개
          2~20                  : 게시물       100
          21~30                : 게시물        37
          => 과연 마지막 구간에서 end값이 30이 맞는가??
               : 실제로는 24로 보정되어야함

              //마지막 페이지 번호를 구하는 공식

              게시물이 351개
              한페이지당 10개씩 배치

              끝페이지 번호 36번

              올림(총 게시물 수 / 한페이지당 배치할 게시물 수)

        * */
        //totalCount : 총 게시물 수
        int finalPage= (int)Math.ceil( (double) totalCount/ pageInfo.getAmount());
        //마지막 구간에서 end 값을 finalPage 로 보정
        if(finalPage < this.end){
            this.end = finalPage;
        }
    }

}
