<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.ecommerceJsp.dao.UserDao, java.util.*, com.ecommerceJsp.bean.User" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration validation</title>
</head>
<body>

<jsp:useBean id= "user" class= "com.ecommerceJsp.bean.User"></jsp:useBean>  
<jsp:setProperty name="user" property="*" />  
<%
//name, email, phone_no, username, password, confirmed_password;
		String name = user.getName();
		String email = user.getEmail();
		String phone_no = user.getPhone_no();
		String u_name =  user.getUsername();
		String password =user.getPassword();
		String confirmed_password = user.getConfirmed_password();
		
		if(UserDao.containsUsername(u_name)){
			//existing username.
			response.sendRedirect("registrationstatus.jsp?status=username_already_exists");
		}else if(UserDao.containsuseremail(email)){
			//existing email.
			response.sendRedirect("registrationstatus.jsp?status=email_already_exists");
		}else if (!(password.equals(confirmed_password))) {
			//passwordmm = password miss-match.
				response.sendRedirect("registrationstatus.jsp?status=password_missmatch");
		} else if (password.length() < 5) {
			//passwords = password short.
				response.sendRedirect("registrationstatus.jsp?status=short_password");
		} else {
			UserDao.adduser(user);
			UserDao.createTable(u_name);
			//passwords = password short.
			response.sendRedirect("registrationstatus.jsp?status=successfully_registred");
		}

%>


</body>
</html>