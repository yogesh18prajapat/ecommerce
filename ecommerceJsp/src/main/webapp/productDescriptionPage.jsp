<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.ecommerceJsp.dao.ProductDao, com.ecommerceJsp.bean.Product"%>

<!DOCTYPE html>
<html lang='en'>
<head>
<title>Product Description</title>
<link rel='stylesheet' href='productDetailPage.css'>
<style>
body {
	height: 100vh;
}

* {
	margin: 0;
	padding: 0;
}

.container {
	background-image: linear-gradient(to right, rgb(201, 195, 195),
		rgb(175, 175, 175));
	height: 100vh;
	display: flex;
}

.left, .right {
	width: 50%;
	padding: 50px;
}
/* .left{
    background-image: linear-gradient(to right,rgb(184, 255, 255), rgb(95, 243, 243) );
} */
.right h1 {
	color: rgb(185, 0, 37);
}

#name {
	font-size: 20px;
	font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande',
		'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
	font-weight: 900;
}

.content {
	text-align: center;
}

#price {
	font-weight: 900;
}

#oldprice {
	font-weight: 100;
}

.buttons {
	width: fit-content;
	padding-left: 30px;
}

.btn1 {
	padding: 20px 75px;
	background-color: rgb(243, 224, 189);
}

.btn2 {
	padding: 20px 75px;
	background-color: rgb(226, 147, 0);
}

#image {
	padding-left: 35px;
	width: 80%;
	height: 500px;
}

.description {
	padding-top: 30px;
	text-align: justify;
	text-indent: 20px;
	white-space: normal;
}

.bottom_btn {
	margin-right: 10px;
	text-align: center;
	width: 40%;
	padding: 20px;
	position: fixed;
	bottom: 1%;
	background-color: antiquewhite;
}

.right a {
	color: rgb(38, 58, 58);
}

.bottom_btn:hover {
	background-color: rgb(241, 196, 137);
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: rgb(117, 112, 112);
	position: fixed;
	top: 0;
	width: 100%;
}
.nav{
    color: aliceblue;
}

li {
	float: left;
}

li a {
	display: block;
	color: rgb(252, 240, 240);
	text-align: center;
	padding: 14px 25px;
	text-decoration: none;
}

li a:hover:not(.active) {
	background-color: rgb(92, 88, 88);
}

.active {
	background-color: rgb(241, 196, 137);
	  color:rgb(80, 27, 27);
	
}
</style>
</head>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Progma", "no-cache");
response.setHeader("Expires", "0");
%>

<%
String user_name = session.getAttribute("username").toString();
System.out.println("homepage: "+user_name);
%>


<%
int id = Integer.parseInt(request.getParameter("id"));
//System.out.println("id by plp: " + id);
Product p = ProductDao.getProductById(id);
request.setAttribute("p",p);

%>
<body>
	 <ul class='navigation'>
    <li><a href="homepage.jsp" class="nav">Home</a></li>
    <li><a href="profilepage.jsp">Profile</a></li>
    <li><a href="cartdisplay.jsp"  class="nav">Cart</a></li>
    <li><a href="orders.jsp"  class="nav">Orders</a></li>
        <li style="float:right"><a class="active" href="logout.jsp">Logout</a></li>
  </ul> 
	<div class='container'>

		<div class='left'>
			<img id='image'
				src='${p.getImg()}'
				alt='image'>
			<div class='content'>
				<p id='name'>${p.getName()}</p>
				<p id='price'>
					<del id='oldprice'>${ p.getOldprice()}</del>
					&nbsp;&nbsp;${p.getPrice()}
				</p>
				<div class='buttons'>
					<a href="cartprocess.jsp?id=${p.getId()}&alert=0">
						<button class='btn1'>ADD TO CART</button>
					</a> <a href='buynowpayment.jsp?id=${p.getId()}'>
						<button class='btn2'>BUY NOW</button>
					</a>

				</div>
			</div>
		</div>

		<div class='right'>
			<h1>DESCRIPTION</h1>
			<hr />
			<div class='description'>
				<p>DESCRIPTION for product: ${ p.getId()}</p>
				<a href='cartdisplay.jsp'>
					<p class='bottom_btn'>GO TO CART</p>
				</a>
			</div>
		</div>
	</div>
</body>
</html>