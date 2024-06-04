package com.study.SpringStudy.springmvc.chap05.dto.response;

import lombok.*;

@Getter@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionDto {
    //좋아요 처리를 위해 클라이어늩에 보낼 json
    private int likeCount;  //갱신된 총 좋아요 수
    private int dislikeCount; // 갱신된 총 싫어요 수
    private String userReaction; //현재 리액션 상태 (안눌렀는지, 좋아요인지 싫어요인지)

}
