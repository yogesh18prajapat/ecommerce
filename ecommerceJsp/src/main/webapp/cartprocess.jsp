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
		int id = Integer.parseInt(request.getParameter("id"));
		int alert = Integer.parseInt(request.getParameter("alert"));

//		System.out.println("cartprocess ID: "+id);
//		session.getAttribute("username");
		ProductDao.addToCart(id, user_name);
//		if(alert == 0){
			response.sendRedirect("cartdisplay.jsp");


//			response.sendRedirect("productslist.jsp");
%>		

<h1>CART PROCESS END</h1>

</body>
</html>