<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isElIgnored=false %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="mB" class="bean.MemberVOEl"/><!-- 객체인스턴스 --> 
<jsp:useBean id="mC" class="bean.MemberVOEl"/><!-- 객체인스턴스 --> 
<jsp:useBean id="memberList" class="java.util.ArrayList"/>

<%
	mB.setId("yang");
	mB.setName("양정원");
	
	mC.setId("park");
	mC.setName("박종성");
	
	memberList.add(mB);
	memberList.add(mC);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el 표현어로 배열 출력</title>
</head>
<body>
	회원 1 id : ${memberList[0].id }<br>
	회원 1 이름 : ${memberList[0].name }<br>
	회원 2 id : ${memberList[1].id }<br>
	회원 2 이름 : ${memberList[1].name }<br>
</body>
</html>