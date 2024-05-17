package com.study.SpringStudy.springmvc;

import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor  //비어있는 기본 생성자 생성 가능~!⭐️
//@AllArgsConstructor // 모든 필드 초기화 생성자
//@RequiredArgsConstructor // 필수 파라미터(final 필드) 초기화 생성자
public class Student {
    private  String name;
    private int age;
    private int grade;


}
