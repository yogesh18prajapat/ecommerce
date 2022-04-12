<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout Page</title>
</head>
<body>
<%
String name = session.getAttribute("username").toString();
System.out.println("logoutpage: "+name);
 session.invalidate();
 response.sendRedirect("loginform.html");
%>


</body>
</html>