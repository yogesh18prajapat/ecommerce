<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Increment-Decrement</title>
</head>
<body>
	<%@page
		import="com.ecommerceJsp.dao.ProductDao, java.util.*, com.ecommerceJsp.bean.Product"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<%
String user_name = session.getAttribute("username").toString();
System.out.println("cartQuantity: "+user_name);
%>

	<% 
	String operation = request.getParameter("operation").toString();
	int product_id = Integer.parseInt(request.getParameter("id"));
	if(operation.equals("increment")){
		ProductDao.updateQunatity(user_name, product_id, true);
		response.sendRedirect("cartdisplay.jsp");
	}else{
		ProductDao.updateQunatity(user_name, product_id, false);
		response.sendRedirect("cartdisplay.jsp");
	}
	 
	%>

</body>
</html>