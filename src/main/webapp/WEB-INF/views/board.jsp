<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/board.css">
</head>
<body>
<h2>게시판</h2>

<c:choose>
    <c:when test="${not empty sessionScope.loginMember}">
        <a href="${pageContext.request.contextPath}/board/write">새 글 작성</a>
        <span> | 
${sessionScope.loginMember.username}님 환영합니다.</span>
        <a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/member/login">로그인 후 글쓰기</a>
        <a href="${pageContext.request.contextPath}/member/register">회원가입</a>
    </c:otherwise>
</c:choose>

<br><br>

<div class="search-container" style="margin-bottom: 20px;">
    <form action="${pageContext.request.contextPath}/board/list" method="get">
        <input type="text" name="searchTerm" placeholder="제목 또는 작성자 검색" value="${searchTerm}"/>
        <button type="submit">검색</button>
    </form>
</div>

<table border="1" width="600">
    <tr>
        <th>글 번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>관리</th>
    </tr>
    <c:forEach var="b" items="${boards}">
        <tr>
            <td>${b.boardId}</td>
            <td><a href="${pageContext.request.contextPath}/board/detail/${b.boardId}">${b.title}</a></td>
            <td>${b.writer}</td>
            <td>
                <c:if test="${not empty sessionScope.loginMember && sessionScope.loginMember.username == b.writer}">
                    <a href="${pageContext.request.contextPath}/board/edit/${b.boardId}">수정</a> |
<a href="${pageContext.request.contextPath}/board/delete/${b.boardId}">삭제</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="${pageContext.request.contextPath}/board/list?page=${currentPage - 1}&searchTerm=${searchTerm}">이전</a>
    </c:if>

    <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="${pageContext.request.contextPath}/board/list?page=${i}&searchTerm=${searchTerm}" 
           class="${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>

    <c:if test="${currentPage < totalPages}">
        <a href="${pageContext.request.contextPath}/board/list?page=${currentPage + 1}&searchTerm=${searchTerm}">다음</a>
    </c:if>
</div>

</body>
</html>