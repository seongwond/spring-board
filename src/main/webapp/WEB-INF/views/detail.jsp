<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/comments.css">
</head>
<body>
<h2>게시글 상세 보기</h2>

<p><strong>ID:</strong> ${board.boardId}</p>
<p><strong>제목:</strong> ${board.title}</p>
<p><strong>내용:</strong> ${board.content}</p>
<p><strong>작성자:</strong> ${board.writer}</p>
<p><strong>작성일:</strong> ${createdDate}</p>
<p><strong>수정일:</strong> ${modifiedDate}</p>

<hr>
<a href="${pageContext.request.contextPath}/board/edit/${board.boardId}">수정</a> |
<a href="${pageContext.request.contextPath}/board/delete/${board.boardId}">삭제</a> |
<a href="${pageContext.request.contextPath}/board/list">목록으로</a>

<div id="comments-section">
    <h3>댓글</h3>
    <div id="comment-list">
        </div>
    
    <div id="comment-form">
        <h4>댓글 작성</h4>
        <textarea id="comment-content" placeholder="댓글 내용을 입력하세요"></textarea>
        <button id="submit-comment">등록</button>
    </div>
</div>

<script>
    const boardId = ${board.boardId};
</script>
<script src="${pageContext.request.contextPath}/js/comments.js"></script>

</body>
</html>