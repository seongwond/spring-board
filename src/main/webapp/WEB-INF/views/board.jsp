<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
    <!-- 외부 CSS 파일 불러오기 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/board.css">
</head>
<body>
<h2>게시판</h2>

<a href="${pageContext.request.contextPath}/board/write">새 글 작성</a>
<br><br>

<table border="1" width="600">
    <tr>
        <th>ID</th>
        <th>제목</th>
        <th>작성자</th>
        <th>관리</th>
    </tr>
    <c:forEach var="b" items="${boards}">
        <tr>
            <td>${b.id}</td>
            <td><a href="${pageContext.request.contextPath}/board/detail/${b.id}">${b.title}</a></td>
            <td>${b.writer}</td>
            <td>
                <a href="${pageContext.request.contextPath}/board/edit/${b.id}">수정</a> |
                <a href="${pageContext.request.contextPath}/board/delete/${b.id}">삭제</a>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- 페이징 버튼 -->
<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="${pageContext.request.contextPath}/board/list?page=${currentPage - 1}">이전</a>
    </c:if>

    <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="${pageContext.request.contextPath}/board/list?page=${i}" 
           class="${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>

    <c:if test="${currentPage < totalPages}">
        <a href="${pageContext.request.contextPath}/board/list?page=${currentPage + 1}">다음</a>
    </c:if>
</div>

</body>
</html>
