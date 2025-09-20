<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 400px; margin: 50px auto; }
        input { width: 100%; padding: 8px; margin: 5px 0; }
        button { padding: 10px 20px; margin-top: 10px; }
    </style>
</head>
<body>
<div class="container">
    <h2>회원가입</h2>
    <form action="/member/register" method="post">
        <label>이메일 (로그인 아이디)</label>
        <input type="email" name="email" placeholder="example@example.com" required>
        
        <label>실명</label>
        <input type="text" name="username" placeholder="홍길동" required>
        
        <label>비밀번호</label>
        <input type="password" name="password" placeholder="비밀번호" required>
        
        <label>비밀번호 확인</label>
        <input type="password" name="confirmPassword" placeholder="비밀번호 확인" required>
        
        <button type="submit">회원가입</button>
    </form>
    <p>이미 계정이 있나요? <a href="/member/login">로그인</a></p>
</div>
</body>
</html>
