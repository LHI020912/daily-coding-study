<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%! String msg="아이디를 입력하지 않았습니다. 아이디를 입력하세요."; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14_loginParamdResult</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String user_id = request.getParameter("uesr_id");
	// user_id가 빈값이면 login page로 제어 이동
	if(user_id.equals("")) { 
%>
	<jsp:forward page="login_param.jsp">
		<jsp:param value="<%=msg %>" name="msg"/>
	</jsp:forward>
<%} %>
<h3>환영합니다<%=user_id %>님</h3>
</body>
</html>