<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
	String loginstatus = request.getParameter("status").toString();
	request.setAttribute("status", loginstatus);

	System.out.println("login status: "+loginstatus);
	
	boolean loggedin_successfuly = true;
	request.setAttribute("loggedin_successfuly", loggedin_successfuly);
	
	if (loginstatus.equals("login_successful")) {
		response.sendRedirect("homepage.jsp");
	}
	%>

	<script type="text/javascript">
		alert("${status}");
    </script>
    
    <c:if test="${loggedin_successfuly}">
    	 <jsp:include page="loginform.html"/>
    </c:if>

</body>
</html>