<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05_el_binding.jsp</title>
<script type="text/javascript">
	let name = "${name}";
	alert(name);
</script>
</head>
<body>
	el 표현언어로 파라미터값 반환받을때는 parm 내장객체 사용/ request 객체의 attritbute값을 반환받을때는 attribute name만 표현<br>
	id: <%=request.getAttribute("id") %>  <br>
	pwd: ${pwd} <br>
	name: ${name } <br>
	email: ${email } <br>

</body>
</html>