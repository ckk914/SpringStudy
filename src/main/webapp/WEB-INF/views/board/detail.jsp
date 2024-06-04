<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>게시판 글쓰기</title>

            <link rel="preconnect" href="https://fonts.googleapis.com">
            <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
            <link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">

            <!-- reset -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

            <!-- fontawesome css: https://fontawesome.com -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
            <!-- bootstrap css -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

            <!— bootstrap js —>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                    defer></script>

                <link rel="stylesheet" href="/assets/css/main.css">


               <link rel="stylesheet" href="/assets/css/detail.css">
        </head>

        <body>
            <%@ include file="../include/header.jsp" %>

                <div id="wrap" class="form-container" data-bno="${bbb.boardNo}">

                    <h1>${bbb.boardNo}번 게시물 내용~ </h1>
                    <h2># 작성일자: ${bbb.regDateTime}</h2>
                    <label for="writer">작성자</label>
                    <input type="text" id="writer" name="writer" value="${bbb.writer}" readonly>
                    <label for="title">제목</label>
                    <input type="text" id="title" name="title" value="${bbb.title}" readonly>
                    <label for="content">내용</label>
                    <div id="content">${bbb.content}</div>
                                    <div class="buttons">
                                        <div class="reaction-buttons">
                                            <button id="like-btn">
                                                <i class="fas fa-thumbs-up"></i> 좋아요
                                                <span id="like-count">0</span>
                                            </button>
                                            <button id="dislike-btn" class="dislike-btn">
                                                <i class="fas fa-thumbs-down"></i> 싫어요
                                                <span id="dislike-count">0</span>
                                            </button>
                                        </div>
                                    
                                        <button class="list-btn" type="button" onclick="window.location.href='${ref}'">
                                            목록
                                        </button>
                                    </div>
                    <!-- <div class="buttons">
                        <button class="list-btn" type="button" onclick="window.location.href='${ref}'">목록</button>
                    </div> -->



                    <!-- 댓글 영역 -->


                    <!-- 댓글 영역 -->

                    <div id="replies" class="row">
                        <div class="offset-md-1 col-md-10">
                            <!-- 댓글 쓰기 영역 -->
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-9">
                                            <div class="form-group">
                                                <label for="newReplyText" hidden>댓글 내용</label>
                                                <textarea rows="3" id="newReplyText" name="replyText"
                                                    class="form-control" placeholder="댓글을 입력해주세요."></textarea>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="newReplyWriter" hidden>댓글 작성자</label>
                                                <input id="newReplyWriter" name="replyWriter" type="text"
                                                    class="form-control" placeholder="작성자 이름"
                                                    style="margin-bottom: 6px;">
                                                <button id="replyAddBtn" type="button"
                                                    class="btn btn-dark form-control">등록
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> <!-- end reply write -->

                            <!--댓글 내용 영역-->
                            <div class="card">
                                <!-- 댓글 내용 헤더 -->
                                <div class="card-header text-white m-0" style="background: #343A40;">
                                    <div class="float-left">댓글 (<span id="replyCnt">0</span>)</div>
                                </div>

                                <!-- 댓글 내용 바디 -->
                                <div id="replyCollapse" class="card">
                                    <div id="replyData">
                                        <!--
                            < JS로 댓글 정보 DIV삽입 >
                        -->
                                    </div>

                                    <!-- 댓글 페이징 영역 -->
                                    <ul class="pagination justify-content-center">
                                        <!--
                            < JS로 댓글 페이징 DIV삽입 >
                        -->
                                    </ul>
                                </div>
                            </div> <!-- end reply content -->
                        </div>
                    </div> <!-- end replies row -->

                    <!-- 댓글 수정 모달 -->
                    <div class="modal fade bd-example-modal-lg" id="replyModifyModal">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">

                                <!-- Modal Header -->
                                <div class="modal-header" style="background: #343A40; color: white;">
                                    <h4 class="modal-title">댓글 수정하기</h4>
                                    <button type="button" class="close text-white" data-bs-dismiss="modal">X</button>
                                </div>

                                <!-- Modal body -->
                                <div class="modal-body">
                                    <div class="form-group">
                                        <input id="modReplyId" type="hidden">
                                        <label for="modReplyText" hidden>댓글내용</label>
                                        <textarea id="modReplyText" class="form-control" placeholder="수정할 댓글 내용을 입력하세요."
                                            rows="3"></textarea>
                                    </div>
                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <button id="replyModBtn" type="button" class="btn btn-dark">수정</button>
                                    <button id="modal-close" type="button" class="btn btn-danger"
                                        data-bs-dismiss="modal">닫기
                                    </button>
                                </div>
                            </div>
                        </div>

                    </div>

                    <!— end replyModifyModal —>
                        <!-- 로딩 스피너 -->
                        <div class="spinner-container" id="loadingSpinner">
                            <div class="spinner-border text-light" role="status">
                                <span class="visually-hidden">Loading...</span>
                            </div>
                        </div>


                </div>


                <!-- 모듈 시작하는 녀석을 호출한다~!⭐️  시작하는 것만~!-->
                <script type="module" src="/assets/js/reply.js">

                </script>
        </body>

        </html>