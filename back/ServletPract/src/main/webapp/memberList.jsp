<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자용</title>
</head>
<body>
<h2>회원 관리 페이지(관리자용)</h2>
<table border="1">
    <tr>
        <th>아이디</th><th>이름</th><th>이메일</th><th>전화번호</th>
    </tr>
    <c:forEach var="mem" items="${membersList}">
        <tr>
            <td>${mem.memId}</td>
            <td>${mem.memPwd}</td>
            <td>${mem.memName}</td>
            <td>${mem.memEmail}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>