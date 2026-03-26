<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var = "contextPath" value ="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>
	<h3>회원관리</h3>
	<hr>
	<a href="${contextPath}/listMember">회원 정보 조회(전용 DBConnect)</a>
	<a href="${contextPath}/listMember2">회원 정보 조회(범용 DBConnect)</a>
</body>
</html>