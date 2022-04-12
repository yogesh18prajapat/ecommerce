<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang='en'>

<head>
    <title>Success Page</title>
    <!-- <link rel='stylesheet' href='successpage.css'> -->
    <style>
        * {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}

.container {
    display: flex;
    flex-direction: column;
    height: 100vh;
    background-image: url('https://wallup.net/wp-content/uploads/2018/09/26/470347-abstract-art-colorful-colors-design-illustration-light-theme-748x421.jpg');
    background-repeat: no-repeat;
    background-size: cover;
    opacity: 0.7;
}
.container:hover{
    opacity: 1;
    transition: 2s;
}
.flex{
display: flex;
justify-content: center;
}

.header:hover,
.footer:hover {
    opacity: 0.7;
    transition: 3s;
    color: rgb(231, 102, 55);
}

.middle:hover {
    color: rgb(81, 112, 168);
    transition: 3s;

}

.header {
    height: 15vh;
    font-size: 50PX;
    font-weight: 1000;
    text-align: center;
    padding-top: 20px;
    color: rgb(48, 83, 2);
}

.middle {
    display: flex;
    justify-content: center;
    height: 70vh;
    font-size: 50PX;
    font-weight: 1000;
    text-align: center;
    color: rgb(255, 5, 67);

}

.footer {
    height: 15vh;
    font-size: 50PX;
    font-weight: 1000;
    text-align: center;
    padding-top: 15px;
    color: rgb(48, 83, 2);
}
.thank{
    border-radius: 180px;
    width: fit-content;
    height: fit-content;
    margin-top: auto;
    margin-bottom: auto;
    padding: 45px;
} 


a{
text-decoration: none;
}
#home{
    width: 30%;
    padding-top: 15px;
    padding-bottom: 5px;
    height: 52px;
    background-image: url('https://wallup.net/wp-content/uploads/2018/09/26/470347-abstract-art-colorful-colors-design-illustration-light-theme-748x421.jpg');
    /* background-image: linear-gradient(to right, crimson, aqua); */
    font-weight: 900;
    font-size: 20px;
    border : 1px solid orange
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
String name = session.getAttribute("username").toString();
%>

    <div class='container'>
        <div class='header flex'>
            <p  class='top_bottom'>PAYMENT SUCCESSFULL</p>
        </div>
        <div class='middle'>
            <div class='thank'>
            <p>THANK YOU</p>
            <p>FOR</p>
            <p>SHOPPING</p>
            <!-- <p>WITH</p> -->
            <!-- <p>US</p> -->
        </div>
        </div>
        <div class='footer flex'>
            
           <a href="homepage.jsp" id='home' >Return to Home page</a>
        </div>
    </div>
</body>

</html>