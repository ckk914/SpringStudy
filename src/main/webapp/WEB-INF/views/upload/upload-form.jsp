<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>File Insert title here</title>
        </head>

        <body>
            <!-- 동기 방식으로 진행 -->
            <h1>/파일 업로드 테스트/</h1>
            <form action="/upload/file" method="post" enctype="multipart/form-data">
                <input type="file" name="thumbnail" id="img-input" accept="image/*">
                <button type="submit">전송</button>
            </form>
        </body>

        </html>