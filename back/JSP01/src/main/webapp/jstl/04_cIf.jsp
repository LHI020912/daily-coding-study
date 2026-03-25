<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="id" value="yang" scope="page"/>
<c:set var="pwd" value="1234" scope="page"/>
<c:set var="name" value="${'양정원' }" scope="page"/>
<c:set var="age" value="${20 }" scope="page"/>
<c:set var="height" value="${176 }" scope="page"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04_cIf</title>
</head>
<body>
	<c:if test="${true }">
		<h3>core의 if 태그 사용</h3>
		c:if의 test속성은 if의 조건식과 같다<br>
		현재 if 는 항상 참입니다. <br>
	</c:if>
	<c:if test="${height>160}">
		<h3>${name}의 키는 160보다 크다아 (0///0)</h3>
	</c:if>
	<c:if test="${id=='yang' and pwd=='1234'}">
		<h3>로그인 성공</h3>
	</c:if>
	<c:if test="${id!='yang'|| pwd!='1234' }">
		<h3>로그인 실패</h3>
	</c:if>
</body>
</html>