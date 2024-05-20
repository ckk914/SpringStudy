package com.study.SpringStudy.springmvc.chap04.dto;

import com.study.SpringStudy.springmvc.chap04.entity.Board;
import lombok.*;

//포장용 객체
//Dto의 필드명은 반드시 html form 태그의 입력 태그들과 맞추어야한다~!
@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardPostDto {

    private String title;
    private String Content;
    private String Writer;

    public Board toEntity() {
        Board b =new Board();
        b.setWriter(this.Writer);
        b.setContent(this.Content);
        b.setTitle(this.title);

        return b;
    }
}
