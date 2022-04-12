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
System.out.println("homepage: "+user_name);
%>

	<%
	String cart_table_name = user_name;
	String order_table_name = user_name +"_orders";
	ProductDao.addToRecentOrder(cart_table_name, order_table_name);
	ProductDao.truncateTable(cart_table_name);
	response.sendRedirect("paymentsuccesspage.jsp");
	%>

</body>
</html>