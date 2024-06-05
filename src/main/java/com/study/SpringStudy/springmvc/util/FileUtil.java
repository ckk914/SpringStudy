package com.study.SpringStudy.springmvc.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {

    /*
     * 사용자가 클라이언트에서 파일을 전송했을 때
     * 중복이 없는 새로운 파일명을 생성하여 해당 파일명으로
     * 날짜별 폴더로 업로드하는 메서드
     *
     * @param file - 사용자가 업로드한 파일의 정보객체
     * @param rootPath - 서버에 업로드할 루트 디렉토리 경로
     *                   ex) D:/spring-prj/upload
     * @return - 업로드가 완료되었을 경우 업로드 된 파일의 위치 경로
     *                   ex)  /2024/05/29/djfalsjdflaksjlfdsaj_고양이.jpg
     */
    public static String uploadFile(String rootPath, MultipartFile file){

        //원본 파일명을 중복이 없는 랜덤 파일명으로 변경
        String newFIleName = UUID.randomUUID()+"_"+ file.getOriginalFilename();

        //파일 업로드 수행
        try {
        file.transferTo(new File(rootPath,newFIleName));
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
