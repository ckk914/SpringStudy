<%@ page contentType="text/html; charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <%@ include file="../include/static-head.jsp" %>

                <!-- 외부 스타일시트 링크 추가 -->
                <link rel="stylesheet" href="/assets/css/member.css" />
        </head>

        <body>
            <%@ include file="../include/header.jsp" %>

                <div class="container wrap">
                    <div class="row">
                        <div class="offset-md-2 col-md-4">
                            <div class="card">
                                <div class="card-header text-white">
                                    <h2><span class="text-gray">MVC</span> 로그인</h2>
                                </div>
                                <div class="card-body">
                                    <form action="/members/sign-in" name="sign-in" method="post" id="signInForm">
                                        <table class="table">
                                            <tr>
                                                <td class="text-left">
                                                    <p><strong>아이디를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span
                                                            id="idCheck"></span></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="text" name="account" id="signInId"
                                                        class="form-control tooltipstered" maxlength="10"
                                                        required="required" placeholder="최대 10자">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-left">
                                                    <p><strong>비밀번호를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span
                                                            id="pwCheck"></span></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="password" size="17" maxlength="20" id="signInPw"
                                                        name="password" class="form-control tooltipstered"
                                                        required="required" placeholder="최소 8자">
                                                </td>
                                            </tr>

                                            <!-- 자동 로그인 체크박스 -->
                                            <tr>
                                                <td>
                                                    <label class="auto-label" for="auto-login">
                                                        <span><i class="fas fa-sign-in-alt"></i>자동 로그인</span>
                                                        <input type="checkbox" id="auto-login" name="autoLogin">
                                                    </label>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td class="text-center">
                                                    <p><strong>로그인하셔서 더 많은 서비스를 이용해보세요!</strong></p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-center" colspan="2">
                                                    <input type="submit" value="로그인"
                                                        class="btn form-control tooltipstered" id="signIn-btn">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-center" colspan="2">
                                                    <a id="sign-up-btn" class="btn form-control tooltipstered"
                                                        href="/members/sign-up">
                                                        회원가입
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-center" colspan="2">
                                                    <a id="custom-login-btn" href="#">
                                                        <img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg"
                                                            width="300" />
                                                    </a>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <script>
                    //서버에서 전송된
                    const result = '${result}';
                    console.log("result " + result);

                    if (result === 'NO_ACC') {
                        alert('아이디가 존재하지 않습니다.');
                    } else if (result === 'NO_PW') {
                        alert('비밀번호가 틀렸습니다.');
                    }

                    // 비회원인 상태로 접근 제한 페이지에 들어갔다 온 경우
                    // 이 경우에는 쿼리스트링(=물음표 뒤에 내용)에 message 파라미터가 붙어있음.⭐️
                    const params = new URLSearchParams(window.location.search);//⭐️
                    const message = params.get('message'); //url에서 message안에 있는 데이터 읽어옴

                    if(message === 'login-required'){
                        alert('로그인이 필요한 서비스입니다.');
                    }

// 쿼리 파라미터를 제거한 새로운 URL 생성
        // window.location.origin -> http://localhost:8383
        // window.location.pathname -> /members/sign-in
        const newUrl = window.location.origin + window.location.pathname;

        // history.replaceState(state, title, url) : 브라우저 조작
        history.replaceState(null, null, newUrl);
                </script>


        </body>

        </html>