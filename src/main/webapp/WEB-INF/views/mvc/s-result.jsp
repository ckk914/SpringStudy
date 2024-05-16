<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1 class="result-title">
        <c:if test="${result == 'f-id'}">아이디가 존재하지 않습니다.</c:if>
        <c:if test="${result == 'f-pw'}">비밀번호 틀렸습니다.</c:if>
        <c:if test="${result == 'success'}">로그인 성공</c:if>
    </h1>


    <a href="/hw/s-login-form">다시 로그인하기</a>
    

</body>
</html>