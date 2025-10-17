<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 작성</title>
</head>
<body>
<h2>새 글 작성</h2>

<form id="writeForm" action="${pageContext.request.contextPath}/board/write" method="post" enctype="multipart/form-data">
    제목: <input type="text" name="title" id="title"/><br><br>
    내용: <textarea name="content" id="content" rows="5" cols="50"></textarea><br><br>
    
    파일: <input type="file" name="file" id="file" accept="image/*"/> <br><br>
    
    <div id="image-preview" style="margin-bottom: 10px;">
        <img id="preview-img" src="#" alt="미리보기 이미지" style="max-width: 300px; display: none;"/>
    </div>
    
    작성자: 
    <span>
        ${sessionScope.loginMember.username}
    </span>
    <br><br>

    <input type="hidden" name="writer" value="${sessionScope.loginMember.username}"/>
    <button type="submit">등록</button>
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
            // 파일을 읽기 위한 FileReader 객체 생성
            const reader = new FileReader();
            
            reader.onload = function(e) {
                previewImg.src = e.target.result; // 이미지 소스 설정
                previewImg.style.display = 'block'; // 이미지 표시
            };
            
            reader.readAsDataURL(file); // 파일 내용을 Data URL 형태로 읽기
        } else {
            previewImg.src = '#';
            previewImg.style.display = 'none'; // 이미지 숨기기
        }
    });

    // =========================================================
    // 기존 유효성 검사 로직
    // =========================================================
    document.getElementById("writeForm").addEventListener("submit", function(e) {
        const title = document.getElementById("title").value.trim();
        const content = document.getElementById("content").value.trim();
        
        // 유효성 검사
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
    });
</script>

</body>
</html>