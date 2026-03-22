<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! String name ="양정원";  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06_include</title>
</head>
<body>
	<!-- top.jsp -->
	이 부분에 top.jsp의 내용이 포함됩니다 <p>
	<%@include file="top.jsp" %>
	<hr>
	<h3>본몬</h3>
	이 부분은 06_include.jsp의 내용입니다.<p>
	<hr>
	<!-- bottom.jsp -->
	이 부분에 bottom.jsp의 내용이 포함됩니다.<p>
	<%@include file="bottom.jsp" %>
</body>
</html>
<!-- top.jsp와 bottom.jsp의 모든 코드가 복사 붙여넣기 됨
	응답되는 페이지에 html/head/body태그들이 중복되어 표현됨
	보통 include되는 페이지는 html/head/body 태그는 표현하지 않는다 -->