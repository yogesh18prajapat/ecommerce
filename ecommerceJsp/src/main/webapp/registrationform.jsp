<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Page</title>
<link rel="shortcut icon" href="favicon/favicon.ico">
<link rel="icon" type="image/gif" href="favicon/animated_favicon1.gif">
<style>
* {
	padding: 0px;
	margin: 0px;
}

.container {
	background-image: url("https://wallpaperaccess.com/full/2483946.jpg");
	background-position: center;
	background-size: cover;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

.form {
	/* border: 1px solid white; */
	border-radius: 4px;
	padding: 2px 5px;
	background-color: white;
	background-image: linear-gradient(to right, rgb(100, 185, 255),
		rgb(181, 219, 241));
}

.flex {
	display: flex;
	justify-content: space-between;
}

.right, .left, .heading, .bottom {
	padding: 13px;
}

.heading {
	padding-top: 0px;
	padding-bottom: 0px;
}

input {
	padding: 8px 10px;
	margin-bottom: 10px;
	border: 2px solid rgb(194, 194, 194);
	box-shadow: 0px 1px 1px rgb(177, 171, 171);
	border-radius: 4px;
}

.bottom label {
	padding-right: 15px;
}

button {
	border: white;
	width: 100%;
	padding: 5px 0px;
	margin-bottom: 20px;
	color: black;
	background-color: rgb(100, 185, 255);
	background-image: linear-gradient(to right, rgb(200, 195, 233),
		rgb(147, 127, 236));
}
</style>
</head>

<body>

	<div class="container">
		<form action="registervalidation.jsp" class="form" method="POST" autocomplete = off>
			<h1 class="heading">Registration</h1>

			<div class="flex">
				<div class="left">
					<label for="fname">Full Name</label><br /> <input type="text"
						id="fname" name="name" placeholder="Enter your name" required /><br />

					<label for="email">Email</label><br /> <input type="email"
						name="email" id="email" placeholder="Enter your Email"
						required /><br /> <label for="password">Password</label><br />
					<input type="password" name="password" id="password"
						placeholder="Enter your password" required /><br />
				</div>

				<div class="right">
					<label for="uname">UserName</label><br /> <input type="text"
						id="uname" name="username" placeholder="Enter your username"
						required /><br /> <label for="phone">Phone Number</label><br />
					<input type="tel" name="phone_no" id="phone"
						placeholder="Enter your number" /><br /> <label
						for="con_password">Confirm Password</label><br /> <input
						type="password" name="confirmed_password" id="con_password"
						placeholder="Confirm your password" required /><br />
				</div>
			</div>
			<button id="submit" name="register" value="register">Register</button>


		</form>

	</div>

	<script src="jQuery.js"></script>

<!-- 	<script>
		$(document).ready(function() {
			$('.form').on('submit', function(event) {
				event.preventDefault();
				console.log("Ajax is workimg................");
				var form_data = $('.form').serialize();

				$.ajax({
					url : 'registrationServlet',
					data : form_data,
					type : 'POST',
					success : function(data) {
						console.log("success is workimg")
						alert(data)
					},
					error : function(data) {
						console.log("error is workimg................");
						$('#status').html('Failed to connect')
					}
				})

				console.log("POST is workimg................");

			});
		});
	</script>   -->
</body>

</html>