package com.study.SpringStudy.core.chap04;

import org.springframework.stereotype.Component;

//@Component  //제어권을 ㅅㅍㄹ 에게
public class KimuraChef implements Chef {

    public void cook(){
        System.out.println("스시의 장인 키무라다.");
    }
}
