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
el표현의 객체 attr을 통한 속성접근은 getter 호출코드로 변환
아이디: ${mem.id }	<br>
비밀번호:${mem.pwd }	<br>
이름: ${mem.name }	<br>
이메일: ${mem.email }	<br>
</body>
</html>