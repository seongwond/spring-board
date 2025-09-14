<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 작성</title>
</head>
<body>
<h2>새 글 작성</h2>

<form id="writeForm" action="${pageContext.request.contextPath}/board/write" method="post">
    제목: <input type="text" name="title" id="title"/><br><br>
    내용: <textarea name="content" id="content" rows="5" cols="50"></textarea><br><br>
    작성자: <input type="text" name="writer" id="writer"/><br><br>
    <button type="submit">등록</button>
</form>

<a href="${pageContext.request.contextPath}/board/list">목록으로</a>

<script>
    document.getElementById("writeForm").addEventListener("submit", function(e) {
        const title = document.getElementById("title").value.trim();
        const content = document.getElementById("content").value.trim();
        const writer = document.getElementById("writer").value.trim();

        if (!title) {
            alert("제목을 입력하세요.");
            e.preventDefault(); // 폼 전송 중단
            return;
        }
        if (!content) {
            alert("내용을 입력하세요.");
            e.preventDefault();
            return;
        }
        if (!writer) {
            alert("작성자를 입력하세요.");
            e.preventDefault();
            return;
        }
    });
</script>

</body>
</html>