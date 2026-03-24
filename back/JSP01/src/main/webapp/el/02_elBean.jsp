<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="student" class="bean.StudentBean" scope="page">
	<jsp:setProperty name="student" property="stdName" value="양정원"/>
</jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${empty student }		<br>
	${not empty student }	<br>
	${stduent.stdName }		<br>
</body>
</html>