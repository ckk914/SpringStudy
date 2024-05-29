package com.study.SpringStudy.springmvc.chap05.entity;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/*
-- 회원 관리 테이블
-- password는 암호화해야해서 굉장히 길게 넣어야함 그냥 넣으면 감옥 ㄱㄱ varchar(150)
CREATE TABLE tbl_member (
    account VARCHAR(50),
    password VARCHAR(150) NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    auth VARCHAR(20) DEFAULT 'COMMON',
    reg_date DATETIME DEFAULT current_timestamp,
    CONSTRAINT pk_member PRIMARY KEY (account)
);
*/
@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
   private String account;
   private String password;
   private String name;
   private String email;
   private Auth auth;
   private LocalDateTime regDate;
}
