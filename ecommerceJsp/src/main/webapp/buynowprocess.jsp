<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="com.ecommerceJsp.dao.ProductDao, java.util.*, com.ecommerceJsp.bean.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String user_name = session.getAttribute("username").toString();
%>

	<%
	int product_id = Integer.parseInt(request.getParameter("id"));
//	request.setAttribute("product_id", product_id);
	
	String order_table_name = user_name +"_orders";
	ProductDao.addToOrderThroughId(product_id, order_table_name);
	response.sendRedirect("paymentsuccesspage.jsp");
	%>

</body>
</html>