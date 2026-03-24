<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13_loginForwardResult</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String user_id = request.getParameter("uesr_id");
	// user_id가 빈값이면 login page로 제어 이동
	if(user_id.equals("")) { 
%>
<jsp:forward page="login_forward.jsp"/>
<%} %>
<h3>환영합니다<%=user_id %>님</h3>
</body>
</html>