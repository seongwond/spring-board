<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 작성</title>
</head>
<body>
<h2>새 글 작성</h2>

<form action="${pageContext.request.contextPath}/board/write" method="post">
    제목: <input type="text" name="title" required/><br><br>
    내용: <textarea name="content" rows="5" cols="50" required></textarea><br><br>
    작성자: <input type="text" name="writer" required/><br><br>
    <button type="submit">등록</button>
</form>

<a href="${pageContext.request.contextPath}/board/list">목록으로</a>

</body>
</html>
