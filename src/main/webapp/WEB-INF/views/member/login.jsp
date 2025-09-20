<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 400px; margin: 50px auto; }
        input { width: 100%; padding: 8px; margin: 5px 0; }
        button { padding: 10px 20px; margin-top: 10px; }
    </style>
</head>
<body>
<div class="container">
    <h2>로그인</h2>
    <form action="/member/login" method="post">
        <label>이메일</label>
        <input type="email" name="email" placeholder="example@example.com" required>
        
        <label>비밀번호</label>
        <input type="password" name="password" placeholder="비밀번호" required>
        
        <button type="submit">로그인</button>
    </form>
    <p>계정이 없으신가요? <a href="/member/register">회원가입</a></p>
</div>
</body>
</html>
