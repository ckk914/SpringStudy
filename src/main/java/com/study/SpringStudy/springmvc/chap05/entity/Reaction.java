package com.study.SpringStudy.springmvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reaction {
    private long reactionId;
    private long boardNo;
    private String account;
    private ReactionType reactionType;
    private LocalDateTime reactionDate;
}
