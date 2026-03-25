<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="id" value="yang" scope="page"/>
<c:set var="pwd" value="1234" scope="page"/>
<c:set var="name" value="${'양정원' }" scope="page"/>
<c:set var="age" value="${20 }" scope="page"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01_cSet</title>
</head>
<body>
	${id }	<br>
	${pwd }	<br>
	${name }<br>
	${age }	<br>
</body>
</html>