<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page
	import="com.ecommerceJsp.dao.ProductDao, java.util.*, com.ecommerceJsp.bean.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ORDERS PAGE</title>
    <style>
       * {
            margin: 0%;
        }

        .footer {
            background-color: darksalmon;
            /* display: flex; */
            color: rgb(16, 61, 16);
            text-shadow: 1px 1px 1px rgb(103, 146, 103);

        }

        .footer>div {
            float: right;
            font-size: 15px;
            margin: 10px;
        }

        .footer .content {
            text-align: center;
            margin: 0%;
            border: 1px solid white;
            padding-top: 20px;
            padding-bottom: 20px;
            width: 25%;
            background-color: rgb(255, 106, 130);
            box-shadow: 0px 1px 10px 1px black;
            margin-bottom: 70px;

        }

        .header {
            background-image: url('https://media.istockphoto.com/vectors/abstract-triangle-pattern-banner-design-vector-id1158980300?k=20&m=1158980300&s=612x612&w=0&h=1tY82ObO8T8-ucCe0XyjTRsGU0Du51UO5aMnGVe43qw=');
            background-repeat: no-repeat;
            background-size: cover;
            text-align: center;
            font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
            font-weight: bolder;
            font-size: 50px;
            margin-bottom: 20px;
            padding: 28px;
            margin-top: 0%;
        }

        .c {
            color: rgb(255, 72, 0);
        }

        .a {
            color: rgb(255, 110, 0);
        }

        .r {
            color: rgb(255, 160, 0);
        }

        .t {
            color: rgb(255, 180, 0);
        }

        .flex-container, .button {
            display: flex;
            background-color: rgb(226, 248, 226);
            border-bottom: 2px solid black;
            margin-bottom: 1px;
            justify-content: center;
        }

        .flex-container>div, .button>a {
            font-size: 15px;
            margin: 0px;
        }

        img {
            padding-left: 40px;
            width: 40px;
            height: 45px;
            overflow: visible;
        }

        img:hover{
            width: 200px;
            height: 200px;
        }

        .content {
            align-self: center;
        }
        .left{
            width: fit-content;
        }

        .middle{
            text-align: left;
        }
        .button{
            border: 1px solid rgb(22, 114, 22);
            border-radius: 20px;
            padding: 3px 30px;
            width: 30px;
        }
        .button>a, .button>span {
            margin: 3px 10px;
        }
        
        .checkout {
            background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKq3X6RPkPQvsKJSSdSj1CO2OTVvf_Sbe7uolao_b-lDobpJEJQVlPaXCC8k4zOCwKeiI&usqp=CAU');
            position: fixed;
            bottom: 0%;
            width: 100%;
            background-color: blanchedalmond;
            font-size: 25px;
            padding: 15px;
            text-align: center;
            margin-bottom: 10px;
        }

        .checkout>a{
            text-decoration: none;
            color: rgb(248, 225, 225);
        }

        .quantity_btn {
            text-decoration: none;
            color: rgb(22, 160, 22);
            font-weight:1000;
        }

        ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
  position: fixed;
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
System.out.println("homepage: "+user_name);
if(user_name == null){
	response.sendRedirect("loginform.html");
}

%>

<%
	String orders_cart_name = user_name+"_orders";
	List<Product> products = ProductDao.getProductsFromOrders(orders_cart_name);
	request.setAttribute("products", products);
%>


    <div class='container'>
        
        <ul class='navigation'>
			<li><a href="homepage.jsp">Home</a></li>
			<li><a href="profilepage.jsp">Profile</a></li>
			<li><a href="cartdisplay.jsp">Cart</a></li>
			<li><a href="orders.jsp">Orders</a></li>
        <li style="float:right"><a class="active" href="logout.jsp">Logout</a></li>
		</ul>
  
  <div style='margin-top:35px'>
     <div class='header'>
        <span class='c'>O</span>
            <span class='a'>R</span>
        <span class='r'>D</span>
       <span class='r'>E</span>
       <span class='t'>R</span>
   <span class='t'>S</span>
</div>



<c:forEach items="${products}" var="product">

      <div class='flex-container'>
        <div class='image' style='flex-grow: 1'><a href="productDescriptionPage.jsp?id=${product.getId()}"><img src='${product.getImg()}' alt="shirt"></a>
        </div>
        <div class='content c1' style='flex-grow: 4'>${product.getName()}</div>
        <div class='content' style='flex-grow: 2'>${product.getQuantity()}</div>
        <div class='content' style='flex-grow: 2'>${product.getPrice()}</div>
        <div class='content' style='flex-grow: 2'>DATE</div>
    </div>

 </c:forEach>
    
    
 </div>
</body>
</html>