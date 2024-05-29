import { BASE_URL } from "./reply.js";
import { fetchInScrollReplies } from "./getReply.js";
//수정 이벤트 등록 함수
export function modifyReplyClickEvent() {
  //수정 모드 진입 이벤트
  document.getElementById(`replyData`).addEventListener(`click`, (e) => {
    e.preventDefault(); //a 링크 기능 제거
    if (!e.target.matches(`#replyModBtn`)) return;
    console.log(`수정 모드 진입`);

    const replyText = e.target.closest(`.row`).firstElementChild.textContent;

    //수정 누르면 기존의 텍스트를 화면으로 가져옴
    document.getElementById(`modReplyText`).value = replyText;
    //댓글 번호 구하기 rno
    const rno = e.target.closest(`#replyContent`).dataset.replyId;
    // 모달창에 클릭한 댓글 번호 달아놓기
    document.querySelector(`.modal`).dataset.rno = rno;
  });
}

//수정 완료 버튼 클릭 시 , 수정 요청 처리
export function modifyCompleteClickEvent() {
  document.getElementById(`replyModBtn`).addEventListener(`click`, (e) => {
    console.log("수정 진입");
    fetchReplyModify();
  });
}

async function fetchReplyModify() {
  const payload = {
    rno: document.querySelector(`.modal`).dataset.rno,
    newText: document.getElementById(`modReplyText`).value,
    bno: document.getElementById(`wrap`).dataset.bno,
  };

  console.log(payload);

  const res = await fetch(BASE_URL, {
    method: `PUT`,
    headers: {
      "content-type": `application/json`,
    },
    body: JSON.stringify(payload),
  });

  if (!res.ok) {
    alert(`수정 실패`);
  }

  //모달 닫기
  document.getElementById(`modal-close`).click();

  window.scrollTo(0, 860);
  await fetchInScrollReplies();
}
