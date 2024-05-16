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


    <h1>주문 내역입니다.</h1>

    <ul>
        <li># 주문하신 메뉴: ${mmm}</li>
        <li># 지불하실 가격: ${ppp}원</li>
    </ul>

    <a href="/coffee/order">재주문하기</a>

</body>
</html>