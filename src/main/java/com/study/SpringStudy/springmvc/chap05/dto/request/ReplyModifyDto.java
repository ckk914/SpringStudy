package com.study.SpringStudy.springmvc.chap05.dto.request;

import com.study.SpringStudy.springmvc.chap05.entity.Reply;
import lombok.*;
import org.checkerframework.checker.units.qual.A;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ReplyModifyDto {
    @NotNull
    private Long rno; // 수정할 댓글의 댓글 번호
    @NotBlank
    private  String newText; //새로운 댓글 내용
    @NotNull
    private Long bno;           //수정 완료 후 새로운 목록을 조회하기 위해

    //엔터티로 변환하는 메서드
    public Reply toEntity(){
        //set
        return Reply.builder()
                .replyText(this.newText)
                .replyNo(this.rno)
                .boardNo(this.bno)
                .build();
    }

}
