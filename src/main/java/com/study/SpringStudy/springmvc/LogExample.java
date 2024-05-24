package com.study.SpringStudy.springmvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
//sout은 시스템에 부담이 되니 , 최대한 쓰지 말자~!
@Component
@Slf4j //로그 라이브러리
public class LogExample {
    /*
        - 로그: 발생시간, 로그 레벨, 파일저장
        - 로그 라이브러리: logback, log4j, slf4j
        - 로그 설정 : application.properties

        - 로그 레벨 (아래로 갈수록 중요 ⭐️ )
        1. trace : 애플리케이션의 실행흐름 세부정보, 디버깅 목적
        2. debug : 변수값, 파라미터값 내부 정보 출력, 디버깅 목적
        ======= 운영 서버 ▽ ===============
        3. info : 운영환경에서 일반적인 작동 정보들, 시스템 상태, 진행중인 작업 정보
        ======== 개발 서버 ▲ =====================
        4. warn : 잠재적인 문제상황을 경고.
            구성값이 예상 범위를 벗어났거나 시스템 리소스 부족
        5. error : 예외가 발생하거나 기능이 실패했을 때 심각한 문제상황
        6. fatal : 치명적인 오류 시스템이 지속될 수 없는 상황, 즉각 조치가 필요한 경우
     */

    public void showLog(){
            //Slf4j 사용한 로그
        log.trace("trace~!");
        log.debug("trace~!");
        log.info("trace~!");
        log.warn("trace~!");
        log.error("trace~!");
    }
}
