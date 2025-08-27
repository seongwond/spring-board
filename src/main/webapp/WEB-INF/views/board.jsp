<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html><%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>게시판</h2>
<a href="write">새 글 작성</a>
<table border="1">
<tr>
    <th>ID</th>
    <th>제목</th>
    <th>내용</th>
    <th>관리</th>
</tr>
<c:forEach items="${boards}" var="b">
<tr>
    <td>${b.id}</td>
    <td>${b.title}</td>
    <td>${b.content}</td>
    <td>
        <a href="edit/${b.id}">수정</a> |
        <a href="delete/${b.id}">삭제</a>
    </td>
</tr>
</c:forEach>
</table>
</body>
</html>
