<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        li {
            color: red;
        }
    </style>
</head>
<body>
    
    <ul>        
       
        <h1>프론트 컨트롤러 v3목록보기</h1>

        <c:forEach var="m" items="${memberList}">
            <li>
               <!-- m.account 는 getter로 가져오는 것!⭐️ -->
                # 아이디: ${m.account},
                <a href="#">
                 #   이름: ${m.userName} 
                </a> 
                &nbsp;&nbsp;&nbsp;
                <a id="rm-btn" href="#">[delete]</a>

            </li>
        </c:forEach>

    </ul>

    <a href="/chap02/v3/join">새로운 회원가입하기</a>


    

</body>
</html>