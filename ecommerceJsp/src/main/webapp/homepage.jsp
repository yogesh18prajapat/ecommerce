<%@page import="javax.websocket.SendResult"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang='en'>
<head>
<title>HOME PAGE</title>
<!-- <link rel='stylesheet' href='homepage.css'> -->
<style>
    @charset "ISO-8859-1";
 .nav {
        background-image: url('https://www.shopping.com/images/banner-image-tall.jpg');
        background-repeat: no-repeat;
        /* object-fit: cover; */
        color: rgb(171, 159, 202);
        text-align: center;
        font-size: 290%;
        font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
        margin: 0%;
        padding: 1%;
        margin-bottom: 1%;
        background-position: center;
        background-size: cover;
        /* margin-top: 50px; */
      }
      .col {
        display: flex;
        justify-content: space-between;
        width: 90%;
        height: 100%;
        margin-bottom: 2%;
        background-repeat: no-repeat;
      }
      .btm {
        text-align: center;
        background-color: #084c79;
        color: white;
        height: 2%;
        width: 90%;
        padding: 1%;
      }
      .s {
        padding: 5px;
      }
      img{
          width: 100%;
          height: 92%;
      }
      a{
        color:white;
        text-decoration: none;
      }


      ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
  /* position: fixed; */
  top: 0;
  width: 100%;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #111;
}

.active {
  background-color: #04AA6D;
}
</style>
</head>
<body>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Progma", "no-cache");
response.setHeader("Expires", "0");
%>

<%
String user_name = session.getAttribute("username").toString();
if(user_name == null){
	response.sendRedirect("loginform.html");
}
%>

    <ul class='navigation'>
        <li><a href="homepage.jsp">Home</a></li>
        <li><a href="profilepage.jsp">Profile</a></li>
        <li><a href="cartdisplay.jsp">Cart</a></li>
        <li><a href="orders.jsp">Orders</a></li>
        <li style="float:right"><a class="active" href="logout.jsp">Logout</a></li>
      </ul>

	<div>
		<h1 class='nav'>
			<!-- <a href='logout'>
				<button class='logout'>LOGOUT</button>
			</a> -->
             Summer <br> sales
		</h1>
	</div>
	<div class='col'> 
	<div>
 			<a href="productslist.jsp?section=men"> 
 			<img
				src='https://m.media-amazon.com/images/I/81ktialOdLL._UX569_.jpg'
				alt='Men Section'></a>
			<div class='btm'>
				<a href='productslist.jsp?section=men'>Men's</a>
			</div>
		</div>
		<div>
			<a href=' productslist.jsp?section=men'><img
				src='https://40plusstyle.com/wp-content/uploads/2019/08/monikahandbag.jpg'
				alt='Women Section'></a>
			<div class='btm'>
				<a href=' productslist.jsp?section=men'>Womens</a>
			</div>
		</div>
		<div>
			<a href='productslist.jsp?section=men'> <img
				src='https://m.media-amazon.com/images/I/81T9k3QhvpL._UY741_.jpg'
				alt='Kid Section'></a>
			<div class='btm s'>
				<a href='productslist.jsp?section=men'>Childern's</a>
			</div>
		</div>
	</div>

</body>
</html>
