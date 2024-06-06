
```dtd
<div class="profile-box">

    <c:choose>
        <c:when test="${login != null && login.profile != null}">
            <img src="${login.profile}" alt="profile image">
        </c:when>
        <c:otherwise>
            <img src="/assets/img/anonymous.jpg" alt="profile image">
        </c:otherwise>
    </c:choose>
</div>
```
- c:when을 여러개로 늘리면 else if 처럼 사용 가능
- c:otherwise는 else처럼 사용
