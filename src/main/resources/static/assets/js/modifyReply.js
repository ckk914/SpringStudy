//수정 이벤트 등록 함수
export function modifyReplyClickEvent() {
  document.getElementById(`replyData`).addEventListener(`click`, (e) => {
    e.preventDefault(); //a 링크 기능 제거
    if (!e.target.matches(`#replyModBtn`)) return;
    console.log(`수정 모드 진입`);

    const replyText = e.target.closest(`.row`).firstElementChild.textContent;

    //수정 누르면 기존의 텍스트를 화면으로 가져옴
    document.getElementById(`modReplyText`).value = replyText;
  });
}

//수정 완료 버튼 클릭 시
export function modifyCompleteClickEvent() {}
