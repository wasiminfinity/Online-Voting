
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/reg.js"></script>
<link href="css/forms.css" rel='stylesheet' type='text/css' />
<title>Admin Login</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<form action="AdminLogin" method="post">
		<ul class="form-style-1">

			<li><label>Email <span class="required">*</span></label> <input
				type="email" name="email" class="field-long"
				placeholder="Enter valid email" /></li>

			<li><label>Password <span class="required">*</span></label> <input
				type="password" name="pass" class="field-long"
				placeholder="Easy to remember,Hard to Guess"></li>


			<!-- <li><a href="forgotpassword.jsp">Forget Password!!</a></li> -->

			<li><input type="submit" value="Login" /></li>
		</ul>
	</form>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
