<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<style>
    body{

    /* image:url('https://i.ibb.co/3493r17/cat.jpg'); */
    /* background-image: url(assets/img/푸들이.gif'); */
    background-image: url('assets/img/푸들이.gif'); 
    background-repeat: no-repeat;
    background-position: 50% -50%;
}
.hello{
    color : #18ad9e;
    font-weight: 300;
    font-size:larger;
    background-color: #8ed01411;
    display: inline;
    
}
.ExeList{
    margin-left: 20px;
}
</style>
</head>
<body>
    <div class = "hello">
    <h1>Hello Spring!!</h1>
</div>
    <br>
    <div class = "ExeList">
    <a href="/chap01/join">회원가입하러가기</a><br>
      <a href="/chap02/v1/join">새로운 회원가입하기(front v1)</a> <br>
       <a href="/chap02/v2/join">새로운 회원가입하기(front v2)</a> <br>
       <a href="/chap02/v3/join">새로운 회원가입하기(front v3)</a> <br>
       <a href="/chap02/v4/join">새로운 회원가입하기(front v4)</a> <br>
       <a href="/chap02/v5/join">새로운 회원가입하기(front v5)</a> <br><br>
       <a href="/score/list">➤성적 정보 관리 프로그램 </a><br>
       <a href="/board/list">➤게시물 관리 프로그램</a><br>
    </div>
</body>
</html>