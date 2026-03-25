<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>c_forEach_ArrayList_result_ex</title>
</head>
<body>
    <h2>상품 목록</h2>
    <table border="1">
        <tr>
            <th>상품번호</th>
            <th>상품명</th>
            <th>가격</th>
            <th>제조사</th>
        </tr>
        <c:forEach var="prd" items="${prdList}">
            <tr>
                <td>${prd.prdNo}</td>
                <td>${prd.prdName}</td>
                <td>${prd.prdPrice}</td>
                <td>${prd.prdCompany}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>