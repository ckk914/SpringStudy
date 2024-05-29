import { BASE_URL } from "./reply.js";
import { showSpinner, hideSpinner } from "./spinner.js";

function getRelativeTime(createAt) {
  // 현재 시간 구하기
  const now = new Date();
  // 등록 시간 날짜타입으로 변환
  const past = new Date(createAt);

  // 사이 시간 구하기 (밀리초)
  const diff = now - past;
  // console.log(diff);

  const seconds = Math.floor(diff / 1000);
  const minutes = Math.floor(diff / 1000 / 60);
  const hours = Math.floor(diff / 1000 / 60 / 60);
  const days = Math.floor(diff / 1000 / 60 / 60 / 24);
  const weeks = Math.floor(diff / 1000 / 60 / 60 / 24 / 7);
  const years = Math.floor(diff / 1000 / 60 / 60 / 24 / 365);

  if (seconds < 60) {
    return "방금 전";
  } else if (minutes < 60) {
    return `${minutes}분 전`;
  } else if (hours < 24) {
    return `${hours}시간 전`;
  } else if (days < 7) {
    return `${days}일 전`;
  } else if (weeks < 52) {
    return `${weeks}주 전`;
  } else {
    return `${years}년 전`;
  }
}

function renderPage({ prev, next, begin, end, pageInfo }) {
  let tag = "";

  if (prev)
    tag += `<li class='page-item'><a class='page-link page-active' href='${
      begin - 1
    }'>이전</a></li>`;

  // 페이지 번호 태그 만들기
  for (let i = begin; i <= end; i++) {
    let active = "";
    if (pageInfo.pageNo === i) active = `p-active`;

    tag += `<li class='page-item ${active}'>
    <a class='page-link page-custom' href='${i}'>${i}</a></li>`;
  }

  // next 만들기
  if (next)
    tag += `<li class='page-item'><a class='page-link page-active' href='${
      end + 1
    }'>다음</a></li>`;

  // 페이지 태그 ul에 붙이기
  const $pageUl = document.querySelector(".pagination");
  $pageUl.innerHTML = tag;
}

export function renderReplies({ pageInfo, replies }) {
  // 댓글 수 렌더링
  document.getElementById("replyCnt").textContent = pageInfo.totalCount;

  // 댓글 목록 렌더링
  let tag = "";
  // replies가 null이 아니거나 0보다 클때
  if (replies && replies.length > 0) {
    replies.forEach(({ reply_no: rno, writer, text, createAt }) => {
      tag += `
        <div id='replyContent' class='card-body' data-reply-id='${rno}'>
            <div class='row user-block'>
                <span class='col-md-3'>
                    <b>${writer}</b>
                </span>
                <span class='offset-md-6 col-md-3 text-right'><b>${getRelativeTime(
                  createAt
                )}</b></span>
            </div><br>
            <div class='row'>
                <div class='col-md-9'>${text}</div>
                <div class='col-md-3 text-right'>
                    <a id='replyModBtn' class='btn btn-sm btn-outline-dark' data-bs-toggle='modal' data-bs-target='#replyModifyModal'>수정</a>&nbsp;
                    <a id='replyDelBtn' class='btn btn-sm btn-outline-dark' href='#'>삭제</a>
                </div>
            </div>
        </div>
        `;
    });
  } else {
    tag = `<div id='replyContent' class='card-body'>댓글이 아직 없습니다! ㅠㅠ</div>`;
  }

  document.getElementById("replyData").innerHTML = tag; //전체 태그 교체 부분

  // 페이지 태그 렌더링
  renderPage(pageInfo);
}

//-->
export function appendReplies({ replies }) {
  // 댓글 목록 렌더링
  let tag = "";
  // replies가 null이 아니거나 0보다 클때
  if (replies && replies.length > 0) {
    // reply_no: rno  => reply_no를 rno로 받아서 처리하겠다.
    replies.forEach(({ reply_no: rno, writer, text, createAt }) => {
      tag += `
        <div id='replyContent' class='card-body' data-reply-id='${rno}'>
            <div class='row user-block'>
                <span class='col-md-3'>
                    <b>${writer}</b>
                </span>
                <span class='offset-md-6 col-md-3 text-right'><b>${getRelativeTime(
                  createAt
                )}</b></span>
            </div><br>
            <div class='row'>
                <div class='col-md-9'>${text}</div>
                <div class='col-md-3 text-right'>
                    <a id='replyModBtn' class='btn btn-sm btn-outline-dark' data-bs-toggle='modal' data-bs-target='#replyModifyModal'>수정</a>&nbsp;
                    <a id='replyDelBtn' class='btn btn-sm btn-outline-dark' href='#'>삭제</a>
                </div>
            </div>
        </div>
        `;
    });
  } else {
    tag = `<div id='replyContent' class='card-body'>댓글이 아직 없습니다! ㅠㅠ</div>`;
  }

  document.getElementById("replyData").innerHTML += tag; //전체 태그 교체 부분
  // 로드된 댓글 수 업데이트
  loadedReplies += replies.length;
}
//<--
//서버에서 댓글 목록 가져오는 비동기 요청 함수
export async function fetchReplies(pageNo = 1) {
  const bno = document.getElementById("wrap").dataset.bno; // 게시물 글번호

  const res = await fetch(`${BASE_URL}/${bno}/page/${pageNo}`);
  const replyResponse = await res.json();
  // { pageInfo: {}, replies: [] }

  // 댓글 목록 렌더링
  renderReplies(replyResponse);
}

//페이지 클릭 이벤트 생성 함수
export function replyPageClickEvent() {
  document.querySelector(`.pagination`).addEventListener(`click`, (e) => {
    e.preventDefault();

    //console.log(e.target.getAttribute(`href`));
    fetchReplies(e.target.getAttribute(`href`));
  });
}

//삭제 개별로 구현
export function replyDeleteClickEvent() {
  //기준 태그로 부터 위로 올라가면서 탐색하는 함수
  // closest(`css selector`);
  document.querySelector(`#replyData`).addEventListener(`click`, (e) => {
    e.preventDefault();
    //삭제
    if (!e.target.matches(`#replyDelBtn`)) return;
    //id 가져옴 data-reply-id    => dataset은 data-  를 의미 .   .reply-id는 -reply-id를 의미⭐️
    const rno = e.target.closest(`#replyContent`).dataset.replyId;

    fetch(`http://localhost:8383/api/v1/replies/${rno}`, {
      method: `DELETE`, //기본값은 get
    })
      .then((res) => res.json())
      .then((json) => {
        renderReplies(json);
        //console.log(json);
      });
  });

  // //아래방향 탐색?
  // const $found = $contentSect
  //   .querySelector(`ul.list:nth-child(2)`)
  //   .querySelector(`li.item:last-child`);
}

// ========== 무한 스크롤 전용 함수 ==============//

let currentPage = 1; // 현재 무한 스크롤 시 진행되고 있는 페이지 번호
let isFetching = false; // 데이터를 불러오는 중에는 더 가져오지 않게 제어하기 위한 논리 변수
let totalReplies = 0; //총 댓글 수
let loadedReplies = 0; //로딩된 댓글 수

//서버에서 댓글 데이터를 페칭(불러옴)
export async function fetchInScrollReplies(pageNo = 1) {
  if (isFetching) return; // 서버에서 데이터를 가져오는 중이면 return;

  isFetching = true; // 현재 가져오는 중이다~!(패칭ing)

  //실질적인 통신 시작 부분
  const bno = document.getElementById("wrap").dataset.bno; // 게시물 글번호
  const res = await fetch(`${BASE_URL}/${bno}/page/${pageNo}`);
  const replyResponse = await res.json();

  if (pageNo === 1) {
    //총 댓글 수 전역변수 값 세팅
    totalReplies = replyResponse.pageInfo.totalCount;
    loadedReplies = 0; // 댓글 입력, 삭제 시 다시 1페이지 로딩시 초기값으로 만들기
    //1페이지에서만 진행(초기 정보 가져오기)
    // 댓글 수 렌더링(가져옴)
    document.getElementById("replyCnt").textContent =
      replyResponse.pageInfo.totalCount;
    //초기 댓글 리셋
    document.getElementById(`replyData`).innerHTML = ``;

    setupInfiniteScroll(); //무한 스크롤 이벤트 등록  : 2페이지 부터는 스크롤 이벤트로 그림~!
  }

  // 댓글 목록 렌더링
  // console.log(replyResponse);
  appendReplies(replyResponse);
  currentPage = pageNo;

  isFetching = false; //패칭 완료
  hideSpinner();

  //댓글을 전부 가져올 시 스크롤 이벤트 제거하기
  if (loadedReplies >= totalReplies) {
    window.removeEventListener(`scroll`, scrollHandler); //모두 가져오면 이벤트 제거
  }
}

//스크롤 이벤트 핸들러
async function scrollHandler(e) {
  //스크롤이 최하단부로 내려갔을 때만 이벤트 발생시켜야 함
  // 현재창에 보이는 세로 길이 + 스크롤을 내린 길이 +500(+값을 주면 미리 주면 미리 나옴) >= 브라우저 전체 세로 길이  (=제일 하단부에 내려왔다는 의미.)
  if (
    window.innerHeight + window.scrollY >= document.body.offsetHeight + 200 &&
    !isFetching
  ) {
    // console.log(e);
    // setTimeout으로 delay 넣으면 안됨(이유:스크롤 여러번하면 한번에 목록이 누적되어 날라옴)
    //서버에서 데이터를 비동기로 불러와야 함
    //2초의 대기열이 생성되면 다음 대기열 생성까지 2초를 기다려야함
    showSpinner();
    await new Promise((resolve) => setTimeout(resolve, 500));
    fetchInScrollReplies(currentPage + 1); //스크롤 마다 +1 페이지 보여줌
  }
}
// 무한 스크롤 이벤트 생성 함수
export function setupInfiniteScroll() {
  window.addEventListener(`scroll`, scrollHandler);
}
