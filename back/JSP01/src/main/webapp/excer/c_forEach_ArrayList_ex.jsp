<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.ProductVo" %>
<%
    ArrayList<ProductVo> prdList = new ArrayList<ProductVo>();
    prdList.add(new ProductVo("P001", "노트북", 1500000, "삼성"));
    prdList.add(new ProductVo("P002", "모니터", 300000, "LG"));
    prdList.add(new ProductVo("P003", "키보드", 50000, "로지텍"));

    request.setAttribute("prdList", prdList);

    RequestDispatcher dispatcher = request.getRequestDispatcher("c_forEach_ArrayList_result_ex.jsp");
    dispatcher.forward(request, response);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>c_forEach_ArrayList_ex</title>
</head>
<body>

</body>
</html>