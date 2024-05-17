package com.study.SpringStudy.webservlet.chap02.v4.Controller;

import com.study.SpringStudy.webservlet.MemberMemoryRepo;
import com.study.SpringStudy.webservlet.MyModel;

import java.util.Map;

public class DeleteController implements ControllerV4{

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    @Override
    public String process(Map<String, String> paramMap, MyModel myModel) {

        // 1, 브라우저에서 삭제 요청이 오면 삭제할 대상의 account를 읽는다.
        String account = paramMap.get("account");
        // 2. 저장소에 연결하여 삭제 처리를 진행시킨다.
        repo.delete(account);
        // 3. 삭제가 완료된 화면을 띄우기 위해 조회요청으로 리다이렉션 한다~!
        return "redirect:/chap02/v4/show";
    }
}
