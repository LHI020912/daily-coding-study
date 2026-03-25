<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="id" value="yang" scope="page"/>
<c:set var="pwd" value="1234" scope="page"/>
<c:set var="name" value="${'양정원' }" scope="page"/>
<c:set var="age" value="${20 }" scope="page"/>
<c:set var="height" value="${176 }" scope="page"/>

<c:remove var="age"/>
<c:remove var="height"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03_cRemove</title>
</head>
<body>
	<table border="1">
		<tr align="center" bgcolor="pink">
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="7%"><b>이름</b></td>
			<td width="7%"><b>나이</b></td>
			<td width="7%"><b>키</b></td>
		</tr>
		<tr align="center" >
			<td>${id}</td>
			<td>${pwd}</td>
			<td>${name}</td>
			<td>${age}</td>
			<td>${height}</td>
		</tr>
	</table>
</body>
</html>