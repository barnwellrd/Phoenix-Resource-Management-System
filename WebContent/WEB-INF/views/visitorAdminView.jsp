<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">  
	<link rel="stylesheet" href="../resources/fontawesome/css/all.css">
	<link rel="stylesheet" href="../resources/css/visitorAdmin.css">   
	<script src="../resources/js/jquery.min.js"></script>  
	<script src="../resources/js/bootstrap.min.js"></script>
	
	<title>Visitor Admin</title>
</head>
<body >

	<div class="center">
		<table>
			<tr>
				<th>First Name.</th>
				<th>Last Name</th>
				<th>Phone</th>
				<th>Check In Time</th>
				<th>Has Checked Out</th>
				<th>Check Out Time</th>
				<th>Check In</th>
				<th>Check Out</th>
			</tr>
			<c:forEach var="ba" items="${alldata}">
			<tr>
				<td>${ba.getFirstName()}</td>
				<td>${ba.getLastName()}</td>
				<td>${ba.getPhone()}</td>
				<td>${ba.getCheckedInTime()}</td>
				<td>${ba.getHasCheckedOut()}</td>
				<td>${ba.getCheckedOutTime()}</td>
				<td><form><button>Check In</button></form></td>
				<td><form action="/Phoenix_Resource_Management_System/Visitor/AdminCO/${ba.getPhone()}"><button>Check Out</button></form></td>
			</tr>
			</c:forEach>
		
		</table>
	</div>
</body>
</html>