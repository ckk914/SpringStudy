package com.study.SpringStudy.springmvc.interceptor;

import com.study.SpringStudy.springmvc.chap04.entity.Board;
import com.study.SpringStudy.springmvc.chap04.mapper.BoardMapper;
import com.study.SpringStudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BoardInterceptor implements HandlerInterceptor {   //HandlerInterceptor 추가

    private final BoardMapper boardMapper;
    //preHandle을 구현하여
    //로그인을 안한 회원은 글쓰기, 글수정, 글삭제 요청을 거부할 것
    //거부하고 로그인 페이지로 리다이렉션을 할 것!
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //요청 url
        String redirectUri = request.getRequestURI();

        log.debug("after login interceptor execute!");
        if (!LoginUtil.isLoggedIn(request.getSession())) {           //로그인이 안되어있으면
            log.info("origin:{}", request.getRequestURI());

            response.sendRedirect("/members/sign-in?message=login-required&redirect=" + redirectUri);  //리다이렉트로 로그인 페이지로 보냄⭐️

            return false;   //true일 경우, 컨트롤러 진입 허용 / false는 진입 차단
        }

        //삭제 요청이 들어오면 서버에서 한번더 체크
        // 관리자인지?
        if(LoginUtil.isAdmin(session)){

            return true;  //통과
        }
        //삭제 요청인지 (url 로 체크)
        //    /board/delete?bno=33 처럼 들어와서 삭제됨
        if(redirectUri.equals("/board/delete")){
            //내가 쓴글인지
            // 현재 삭제하려는 글의 글쓴이 계정명과
            //-> DB에서 조회해보면 됨 -> 글번호가 필요⭐️
            int bno = Integer.parseInt(request.getParameter("bno"));
            Board board = boardMapper.findOne(bno);     //  조회
            String boardAccount = board.getAccount();    // 계정 가져오기

            //현재 로그인한 회원의 계정명을 구해서 (세션에서)
            String loggedInUserAccount = LoginUtil.getLoggedInUserAccount(session);

            // 대조해보는 작업이 필요함
            if (!isMine(boardAccount, loggedInUserAccount)) {
                response.setStatus(403);  //클라이언트가 서버에 접근하려고 했지만, 서버가 요청을 거부했다는 것
                response.sendRedirect("/access-deny?message=authorization");
                return false;
            }

        }
        return true;
    }

    //대조 작업
    private boolean isMine(String boardAccount, String loggedInUserAccount) {
    return boardAccount.equals(loggedInUserAccount);
    }
}
