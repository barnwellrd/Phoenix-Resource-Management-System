<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script>

</script>

<title>Register Now!</title>
</head>
<body>

	<h1> Register to PRMS Services! </h1>
	
	<form action ="registerVisitor" method="post">
	
		First Name: <input type="text" name="visitorFirstName" required/>
		Last Name: <input type="text" name="visitorLastName" required/>
		<br>
		Email Address: <input type="email" name="visitorEmail" />
		<br>
		Phone Number: <input type="text" name="visitorPhone"/>
		<br>
		Host Name: <input type="text" name="visitorVisitingName" />
		<br>
		Visit Purpose: <input type="text" name="visitorPurpose"/>
		<p>
			<input type="radio" name="visitorCompanyName" value="Syntel">Syntel<br>
			<input type="radio" name="visitorCompanyName" value="AmericanExpress">American Express<br>
			<input type="radio" name="visitorCompanyName" value="FedEx">FedEx<br>
			<input type="radio" name="visitorCompanyName" value="GuitarCenter">Guitar Center<br>
			<input type="radio" name="visitorCompanyName" value=""> Other <input type="text" id="other_reason" name="other_reason" />
		</p>
		
		<br>
		<input type="submit" value="Register"/>
	
	</form>




</body>
</html>