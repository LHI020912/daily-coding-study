<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06_el_binding2</title>
</head>
<body>
<% request.setCharacterEncoding("utf-8"); %>
<h3>회원정보 수정</h3>
<form method="post" action="memberUpdate.jsp">
	아이디: <input type="text" name="id" value="${mem.id}" readonly>	<br>
	비밀번호: <input type="password" name="pwd" value="${mem.pwd }">	<br>
	이름: <input type="text" name="name" value="${mem.name }">		<br>
	이메일: <input type="email" name="email" value="${mem.email }">	<br>
	<input type="submit" value="수정완료">
</form>
</body>
</html>