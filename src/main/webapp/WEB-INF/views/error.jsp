<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>에러 발생</title>
</head>
<body>
    <h2>에러가 발생했습니다 😢</h2>
    <p>${message}</p>
    <a href="${pageContext.request.contextPath}/board/list">게시판 목록으로</a>
</body>
</html>
