<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 페이지</title>
</head>
<body>
	<p class ="cls1"><h4 align="center">제품 정보</h4></p>
	<table align="center" border="1">
		<tr align="center" bgcolor="pink">
			<td width="7%"><b>번호</b></td>
			<td width="7%"><b>이름</b></td>
			<td width="7%"><b>가격</b></td>
			<td width="7%"><b>제조사</b></td>
		</tr>
		<c:choose>
			<c:when test="${empty prdList}">
				<td colspan="4">등록된 제품이 없습니다.</td>
			</c:when>
			<c:otherwise>
				<c:forEach var="product" items="${prdList}">
					<tr>
						<td>${product.prdNo}</td>
						<td>${product.prdName}</td>
						<td>${product.prdPrice}</td>
						<td>${product.prdCompany}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>