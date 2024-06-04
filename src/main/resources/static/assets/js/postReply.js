import { BASE_URL } from "./reply.js";
import { fetchInfScrollReplies } from "./getReply.js";
import { callApi } from "./api.js";

// 서버에 댓글 등록을 요청하는 비동기 함수
export const fetchReplyPost = async () => {
  const textInput = document.getElementById("newReplyText");
  const writerInput = document.getElementById("newReplyWriter");

  // 서버로 보낼 데이터
  const payload = {
    text: textInput.value,
    author: writerInput.value,
    bno: document.getElementById("wrap").dataset.bno,
  };
  console.log(payload);

  await callApi(BASE_URL, "POST", payload);

  // const res = await fetch(`${BASE_URL}`, {
  //   method: 'POST',
  //   headers: {
  //     'content-type': 'application/json'
  //   },
  //   body: JSON.stringify(payload)
  // });

  // // console.log('res:', res);
  // if (res.status === 403) {
  //   alert('로그인이 필요한 서비스입니다.');
  //   window.location.href = '/members/sign-in';
  //   return;
  // }

  // const replies = await res.json();

  textInput.value = "";
  // writerInput.value = "";

  // console.log(replies);
  // renderReplies(replies);
  fetchInfScrollReplies();
  window.scrollTo(0, 0);
};
