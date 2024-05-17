<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">

  <!-- reset -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

  <!-- fontawesome css: https://fontawesome.com -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">

  <link rel="stylesheet" href="/assets/css/main.css">
  <link rel="stylesheet" href="/assets/css/list.css">

</head>

<body>

<div id="wrap">

  <div class="main-title-wrapper">
    <h1 class="main-title">꾸러기 게시판</h1>
    <button class="add-btn">새 글 쓰기</button>
  </div>

  <div class="card-container">


    <c:forEach var="b" items="${bList}">
    <div class="card-wrapper">
  
        <section class="card" data-bno="${b.boardNo}">
          <div class="card-title-wrapper">
            <h2 class="card-title">${b.title}</h2>
            <div class="time-view-wrapper">
              <div class="time">
                <i class="far fa-clock"></i>
                  ${b.regDateTime}</div>
              <div class="view">
                <i class="fas fa-eye"></i>
                <span class="view-count">${b.viewCount}</span>
              </div>
            </div>
          </div>
          <div class="card-content">     ${b.content}
      
       

          </div>
        </section>
        <div class="card-btn-group">
          <button class="del-btn" data-href="/board/delete?bn=${b.boardNo}">
            <i class="fas fa-times"></i>
          </button>
        
        </div>
      </div>
</c:forEach>

  </div>

</div>

<!-- 모달 창 -->
<div class="modal" id="modal">
  <div class="modal-content">
    <p>정말로 삭제할까요?</p>
    <div class="modal-buttons">
      <button class="confirm" id="confirmDelete"><i class="fas fa-check"></i> 예</button>
      <button class="cancel" id="cancelDelete"><i class="fas fa-times"></i> 아니오</button>
    </div>
  </div>
</div>



<script>

  const $cardContainer = document.querySelector('.card-container');

  //================= 삭제버튼 스크립트 =================//
  const modal = document.getElementById('modal'); // 모달창 얻기
  const confirmDelete = document.getElementById('confirmDelete'); // 모달 삭제 확인버튼
  const cancelDelete = document.getElementById('cancelDelete'); // 모달 삭제 취소 버튼

  $cardContainer.addEventListener('click', e => {
    // 삭제 버튼을 눌렀다면~
    if (e.target.matches('.card-btn-group *')) {
      console.log('삭제버튼 클릭');
      modal.style.display = 'flex'; // 모달 창 띄움

      const $delBtn = e.target.closest('.del-btn');
      const deleteLocation = $delBtn.dataset.href;

      // 확인 버튼 이벤트
      confirmDelete.onclick = e => {
        // 삭제 처리 로직
        window.location.href = deleteLocation;

        modal.style.display = 'none'; // 모달 창 닫기
      };


      // 취소 버튼 이벤트
      cancelDelete.onclick = e => {
        modal.style.display = 'none'; // 모달 창 닫기
      };
    } else { // 삭제 버튼 제외한 부분은 글 상세조회 요청

      // section태그에 붙은 글번호 읽기
      const bno = e.target.closest('section.card').dataset.bno;
      // 요청 보내기
      window.location.href= '/board/detail?bno=' + bno;
    }
  });

  // 전역 이벤트로 모달창 닫기
  window.addEventListener('click', e => {
    if (e.target === modal) {
      modal.style.display = 'none';
    }
  });

  //========== 게시물 목록 스크립트 ============//

  function removeDown(e) {
    if (!e.target.matches('.card-container *')) return;
    const $targetCard = e.target.closest('.card-wrapper');
    $targetCard?.removeAttribute('id', 'card-down');
  }

  function removeHover(e) {
    if (!e.target.matches('.card-container *')) return;
    const $targetCard = e.target.closest('.card');
    $targetCard?.classList.remove('card-hover');

    const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
    $delBtn.style.opacity = '0';
  }



  $cardContainer.onmouseover = e => {

    if (!e.target.matches('.card-container *')) return;

    const $targetCard = e.target.closest('.card');
    $targetCard?.classList.add('card-hover');

    const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
    $delBtn.style.opacity = '1';
  }

  $cardContainer.onmousedown = e => {

    if (e.target.matches('.card-container .card-btn-group *')) return;

    const $targetCard = e.target.closest('.card-wrapper');
    $targetCard?.setAttribute('id', 'card-down');
  };

  $cardContainer.onmouseup = removeDown;

  $cardContainer.addEventListener('mouseout', removeDown);
  $cardContainer.addEventListener('mouseout', removeHover);

  // write button event
  document.querySelector('.add-btn').onclick = e => {
    window.location.href = '/board/write';
  };



</script>

</body>

</html>