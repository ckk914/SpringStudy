package com.study.SpringStudy.springmvc.chap04.common;

import lombok.*;

@Getter@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Page {
    private int pageNo;  //클라이언트가 요청한 페이지 번호
                                        //= getPageNo()
    private int amount; //클라이언트가 요청한 게시물 목록  ( 한페이지당 게시물 목록 수 )
                                        //= getamount();

    //Apple = 5 로 인식⭐️
    public int getApple(){
        System.out.println("get Apple call check");
        return 5;
    }

    //list/   파라미터 없이 들어오면 기본값으로 사용⭐️
    public Page(){
        this.pageNo= 1;
        this.amount = 6;
    }

    public void setPageNo(int pageNo) {
        //이상한 페이지 넘버를 기입했을때 강제 1로 바꿈.⭐️
        if(pageNo<1  || pageNo > Integer.MAX_VALUE){
            this.pageNo=1;
            return;
        }
        this.pageNo = pageNo;
    }

    //이상한 값 넣었을때 예외 처리
    public void setAmount(int amount) {
        if(amount < 6 ||amount> 60){
            this.amount = 6;
            return;
        }
        this.amount = amount;
    }

    /**
     *
     * 만약에 한페이지에 게시물을 10개씩 렌더링 한다면
     *
     * 1 페이지-> limit 0,10
     * 2 페이지 -> limit 10,10
     * 3 페이지  -> limit 20,10
     *
     *      * 1페이지-> limit 0,6
     *      * 2 페이지 -> limit 6,6
     *      * 3 페이지  -> limit 12,6
     *
     *     게시물을 n개씩 렌더링한다면
     *      *      * 1페이지-> limit 0,n
     *      *      * 2 페이지 -> limit 6,N
     *      *      * 3 페이지  -> limit 12,N
     *                m 페이지 -> limit (m-1)*N , N
     * @return
     */
    //PageStart 호출하면 해당걸로 들어간다~!
    // #{pageStart}
    public int getPageStart(){

        return (this.pageNo -1) * this.amount;
    }
}
