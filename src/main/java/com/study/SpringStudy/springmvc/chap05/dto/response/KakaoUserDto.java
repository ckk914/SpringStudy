package com.study.SpringStudy.springmvc.chap05.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Properties;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoUserDto {
    private Long id;
    @JsonProperty("connected_at")
    private LocalDateTime connectedAt;

    private Properties properties;  //Properties 형= 객체

    @Getter @ToString
    public static class Properties{
        private String nickname;
        @JsonProperty("profile_image")
        private String profileImage;
    }
}
