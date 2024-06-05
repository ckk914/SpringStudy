package com.study.SpringStudy.springmvc.chap05.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.SpringStudy.springmvc.chap05.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;

@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyDetailDto {           //서버  > > 클라이언트    : 뿌려주는 위치  클라이언트가 원하는걸 맞춰줄 수 있다~!
    @JsonProperty("reply_no")  //이거 하면 필드명 바꿔서 뿌려줄 수 있다~! ⭐️
    private long rno;   //요청한 대로 여기서 필드이름 수정하기~!dto
    private String text;
    private String writer;
//    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm")
    private LocalDateTime createAt;
    private String account;
    private String profileImg;        //프로필 경로

    //엔터티를 DTo로 변환하는 생성자
    public ReplyDetailDto(Reply r){
        this.rno = r.getReplyNo();
        this.text = r.getReplyText();
        this.writer = r.getReplyWriter();
        this.createAt = r.getReplyDate();
        this.account = r.getAccount();
        this.profileImg = r.getProfileImg();
    }
}
