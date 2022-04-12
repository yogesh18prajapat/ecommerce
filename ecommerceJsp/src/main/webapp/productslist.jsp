<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@page import="com.ecommerceJsp.dao.ProductDao,
java.util.*, com.ecommerceJsp.bean.Product"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ page
errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>PRODUCT LIST</title>
    <style type="text/css">
      * {
        padding: 0;
        margin: 0%;
      }

      .cards_container {
        padding-left: 40px;
        margin-top: 30px;
      }

      .card {
        float: left;
        margin: 27px;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        box-sizing: border-box;
        transition: 0.3s;
        width: 15%;
        border-radius: 15px;
      }

      .container {
        padding: 2px 16px;
        background-color: darkseagreen;
      }

      .container a {
        color: black;
        text-decoration: none;
        text-align: center;
      }

      .card img {
        width: 100%;
        height: 200px;
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
        color: rgb(80, 27, 27);
      }
	  .buttons{
		  padding-left: 5px;
	  }

      .btn {
        width: 75px;
	    	padding: 5px 0px;
        background-color: rgb(255, 140, 46);
        font-size: 13px;
        font-weight: bolder;
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
    String section = request.getParameter("section");
    List<Product> products = ProductDao.getProducts("products", "men");
    request.setAttribute("products", products);
  //	String link = "productDescriptionPage.jsp?id=";
  //	request.setAttribute("link", link);
  
    %>

    <ul class="navigation">
      <li><a href="homepage.jsp" class="nav">Home</a></li>
      <li><a href="profilepage.jsp">Profile</a></li>
      <li><a href="cartdisplay.jsp" class="nav">Cart</a></li>
      <li><a href="orders.jsp" class="nav">Orders</a></li>
        <li style="float:right"><a class="active" href="logout.jsp">Logout</a></li>
    </ul>
    <div class="cards_container">
      <c:forEach items="${products}" var="product">
      <c:set var="link" value="productDescriptionPage.jsp?id=${product.getId()}"></c:set>
      <div class="card">
        <a href="${link}"
          ><img src="${product.getImg()}" alt="name" style="width: 100%"
        /></a>
        <div class="container">
          <a href="${link}">
            <h4><b>${product.getName()}</b></h4>
            <p><del>${product.getOldprice()}</del></p>
            <p>
              <b>${product.getPrice()}</b>
            </p>
          </a>
          <div class="buttons">
            <a href="cartprocess.jsp?id=${product.getId()}&alert=1"><button class="btn">Add to cart</button></a>
            <a href="buynowpayment.jsp?id=${product.getId()}"><button class="btn">buy now</button></a>
          </div>
        </div>
      </div>

     
      </c:forEach>
    </div>
  </body>
</html>
