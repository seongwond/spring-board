<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 수정</title>
</head>
<body>
<h2>글 수정</h2>

<form action="${pageContext.request.contextPath}/board/edit/${board.boardId}" method="post">
    제목: <input type="text" name="title" value="${board.title}" required/><br><br>
    내용: <textarea name="content" rows="5" cols="50" required>${board.content}</textarea><br><br>
    작성자: 
    <span>
        ${sessionScope.loginMember.username}
    </span>
    <br><br>

    <!-- 실제 전송은 숨겨진 필드로 -->
    <input type="hidden" name="writer" value="${sessionScope.loginMember.username}"/>
    <button type="submit">수정 완료</button>
</form>

<a href="${pageContext.request.contextPath}/board/list">목록으로</a>

</body>
</html>
