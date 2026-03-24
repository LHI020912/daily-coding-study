<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el_binding_forward</title>
</head>
<body>
<%
	request.setAttribute("id","yong");
	request.setAttribute("pwd","1234");
	request.setAttribute("name","양정원");
	request.setAttribute("email","yong@test.com");
%>
<jsp:forward page="05_el_bind.jsp"/>
JSP 액션태그 forward는 제어를 이동할때 현재 페이지의 request, reponse를 전달함<br>
request 객체에 데이터 바인딩해서 전달 가능
</body>
</html>