import {
  fetchInScrollReplies,
  setupInfiniteScroll, //무한 스크롤
  replyDeleteClickEvent,
} from "./getReply.js";
import { fetchReplyPost } from "./postReply.js";

// ====== 전역 변수 ========
export const BASE_URL = "http://localhost:8383/api/v1/replies";

// ====== 실행 코드 ========

// 댓글 목록 서버에서 불러오기
// fetchReplies();
fetchInScrollReplies(); //일단 1페이지 데이터 그려놓기
setupInfiniteScroll(); //무한 스크롤 이벤트 등록  : 2페이지 부터는 스크롤 이벤트로 그림~!

// 댓글 작성 이벤트 등록
document.getElementById("replyAddBtn").addEventListener("click", (e) => {
  // 댓글 등록 로직
  fetchReplyPost();
});

//댓글 페이지 클릭 이벤트 등록
// replyPageClickEvent();

//댓글 삭제 클릭 이벤트 등록
replyDeleteClickEvent();
