<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
</head>
<body>

    <h1>[${name}] 취미 목록</h1>
    <ol>
        <%-- 이건 jsp 주석임~! --%>
        <%-- for (String h : hList) --%>
        <c:forEach var="h" items="${hobbies}">
            <%-- 주석주석 --%>
            <li>${h}</li>
        </c:forEach>

    </ol>
<h2>나의 전공 =>${major}</h2>
</body>
</html>