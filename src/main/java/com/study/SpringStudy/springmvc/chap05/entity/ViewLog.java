package com.study.SpringStudy.springmvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

@Setter@Getter@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewLog {
    private long id;
    private String account;
    private long boardNo;
    private LocalDateTime viewTime;
}
