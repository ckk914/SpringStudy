package com.study.SpringStudy.springmvc.chap05.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessTokenDto {
    //Json 원래 키값을 입력해서 받아온다~!
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;


}
