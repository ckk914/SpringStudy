package com.study.SpringStudy.springmvc.chap05.service;

import com.study.SpringStudy.springmvc.chap05.dto.response.ReactionDto;
import com.study.SpringStudy.springmvc.chap05.entity.Reaction;
import com.study.SpringStudy.springmvc.chap05.entity.ReactionType;
import com.study.SpringStudy.springmvc.chap05.mapper.ReactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionMapper reactionMapper;


    // 공통 리액션 DB처리 메서드
    private Reaction handleReaction(long boardNo
            , String account
            , ReactionType newReactionType) {

        // 처음 리액션을 한다? -> 좋아요든 싫어요든 INSERT
        // 기존 리액션을 취소한다? -> 기존 데이터를 DELETE
        // 기존 리액션을 변경한다?
        // -> 기존 리액션 데이터를 DELETE 후 새로운 리액션을 INSERT

        // 현재 게시물에 특정 사용자가 리액션을 했는지 확인
        Reaction existingReaction = reactionMapper.findOne(boardNo, account);

        // 새 라이크 리액션 객체
        Reaction newReaction = Reaction.builder()
                .account(account)
                .boardNo(boardNo)
                .reactionType(newReactionType)
                .build();

        if (existingReaction != null) { // 처음 리액션이 아닌 경우
            if (existingReaction.getReactionType() == newReactionType) {
                // 동일한 리액션이기 때문에 취소
                reactionMapper.delete(boardNo, account);
            } else {
                // 리액션 변경
                reactionMapper.delete(boardNo, account); // 기존 리액션 취소
                reactionMapper.save(newReaction);   // 새 리액션 생성
            }
        } else {
            // 처음 리액션을 한 경우
            reactionMapper.save(newReaction); // 새 리액션 생성
        }
    return reactionMapper.findOne(boardNo, account);
    }


    // 좋아요 중간처리
    public ReactionDto like(long boardNo, String account) {
        Reaction reaction = handleReaction(boardNo, account, ReactionType.LIKE);

        return getReactionDto(boardNo, reaction);
    }

    private ReactionDto getReactionDto(long boardNo, Reaction reaction) {
        String reactionType = null;
        if(reaction != null){
            reactionType = reaction.getReactionType().toString();
        }

        return ReactionDto.builder()
                .likeCount(reactionMapper.countLikes(boardNo))
                .dislikeCount(reactionMapper.countDislikes(boardNo))
                .userReaction(reactionType)
                .build();
    }

    // 싫어요 중간처리
    public ReactionDto dislike(long boardNo, String account) {
        Reaction reaction = handleReaction(boardNo, account, ReactionType.DISLIKE);
        return getReactionDto(boardNo, reaction);

    }
}
