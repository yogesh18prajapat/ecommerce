<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login Validation</title>


<%@ page
	import="com.ecommerceJsp.dao.UserDao, java.util.*, com.ecommerceJsp.bean.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String user_name = request.getParameter("user_name");
String password = request.getParameter("user_password");

if(UserDao.containsUsername(user_name)){
	if(UserDao.validatePassword(user_name, password)){
		response.sendRedirect("loginstatus.jsp?status=login_successful");
		session.setAttribute("username", user_name);
	}
	else{
		System.out.println("incorrect password");
		response.sendRedirect("loginstatus.jsp?status=incorrect_password");
	}
}else{
	System.out.println("incorrect username");
	response.sendRedirect("loginstatus.jsp?status=incorrect_username");
}


%>

</head>
<body>

</body>
</html>