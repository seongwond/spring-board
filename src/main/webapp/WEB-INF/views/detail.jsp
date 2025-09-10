<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세</title>
</head>
<body>
<h2>게시글 상세 보기</h2>

<p><strong>ID:</strong> ${board.id}</p>
<p><strong>제목:</strong> ${board.title}</p>
<p><strong>내용:</strong> ${board.content}</p>
<p><strong>작성자:</strong> ${board.writer}</p>
<p><strong>작성일:</strong> ${createdDate}</p>
<p><strong>수정일:</strong> ${modifiedDate}</p>

<hr>
<a href="${pageContext.request.contextPath}/board/edit/${board.id}">수정</a> |
<a href="${pageContext.request.contextPath}/board/delete/${board.id}">삭제</a> |
<a href="${pageContext.request.contextPath}/board/list">목록으로</a>

</body>
</html>
