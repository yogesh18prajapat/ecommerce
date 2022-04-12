<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	String registrationstatus = request.getParameter("status").toString();
	request.setAttribute("status", registrationstatus);

	System.out.println(registrationstatus);
	boolean registered_successfuly = false;
	if (registrationstatus.equals("successfully_registred")) {
		registered_successfuly = true;
//		response.sendRedirect("loginform.html"); 
	}
	request.setAttribute("registered", registered_successfuly);
	%>

	<script type="text/javascript">
		alert("${status}");
    </script>
    
    <c:choose>  
    <c:when test="${registered}">  
    	 <jsp:include page = "loginform.html"></jsp:include>
    </c:when>
    <c:otherwise>  
	 <jsp:include page = "registrationform.jsp"></jsp:include>
    </c:otherwise>  
</c:choose> 
    

	 
</body>
</html>