<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/comment.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/board.css">
</head>
<body>
<h2>게시글 상세 보기</h2>

<p><strong>ID:</strong> ${board.boardId}</p>
<p><strong>제목:</strong> ${board.title}</p>
<p><strong>내용:</strong> ${board.content}</p>
<p><strong>작성자:</strong> ${board.writer}</p>
<p><strong>작성일:</strong> ${createdDate}</p>
<p><strong>수정일:</strong> ${modifiedDate}</p>

<c:if test="${not empty board.fileName}">
    <p>
        <strong>첨부 파일:</strong> 
        <a href="${pageContext.request.contextPath}/board/download/${board.boardId}">${board.fileName}</a>
    </p>

    <c:set var="fileName" value="${board.fileName}" />
    <c:set var="isImage" value="false" />
    
    <c:choose>
        <c:when test="${fn:endsWith(fileName, '.jpg') or fn:endsWith(fileName, '.jpeg') or fn:endsWith(fileName, '.png') or fn:endsWith(fileName, '.gif')}">
            <c:set var="isImage" value="true" />
        </c:when>
    </c:choose>
    
    <c:if test="${isImage}">
        <div style="margin-top: 15px;">
            <img src="${pageContext.request.contextPath}/board/download/${board.boardId}" 
                 alt="${board.fileName}" 
                 style="max-width: 500px; height: auto; border: 1px solid #ccc; display: block;"/>
        </div>
    </c:if>
</c:if>

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
    const contextPath = "${pageContext.request.contextPath}";
    const loginMemberUsername = "${sessionScope.loginMember.username}";
</script>
<script src="${pageContext.request.contextPath}/js/comments.js"></script>

</body>
</html>