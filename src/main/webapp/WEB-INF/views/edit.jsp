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

<form action="${pageContext.request.contextPath}/board/edit/${board.boardId}" method="post" enctype="multipart/form-data">
    제목: <input type="text" name="title" value="${board.title}" required/><br><br>
    내용: <textarea name="content" rows="5" cols="50" required>${board.content}</textarea><br><br>
    
    파일: <input type="file" name="file" id="file" accept="image/*"/><br><br>
    
    <div id="image-preview" style="margin-bottom: 10px;">
        <img id="preview-img" src="#" alt="미리보기 이미지" style="max-width: 300px; display: none;"/>
    </div>
    
    <c:if test="${not empty board.fileName}">
        <p>현재 파일: ${board.fileName}</p>
    </c:if>

    작성자: 
    <span>
        ${sessionScope.loginMember.username}
    </span>
    <br><br>

    <input type="hidden" name="writer" value="${sessionScope.loginMember.username}"/>
    <button type="submit">수정 완료</button>
</form>

<a href="${pageContext.request.contextPath}/board/list">목록으로</a>

<script>
    // =========================================================
    // 파일 미리보기 기능 (클라이언트 측)
    // =========================================================
    document.getElementById('file').addEventListener('change', function(e) {
        const file = e.target.files[0];
        const previewImg = document.getElementById('preview-img');

        if (file && file.type.startsWith('image/')) {
            const reader = new FileReader();
            
            reader.onload = function(e) {
                previewImg.src = e.target.result; 
                previewImg.style.display = 'block';
            };
            
            reader.readAsDataURL(file);
        } else {
            previewImg.src = '#';
            previewImg.style.display = 'none';
        }
    });
    
    // TODO: 기존 유효성 검사 로직이 있다면 여기에 포함
</script>

</body>
</html>