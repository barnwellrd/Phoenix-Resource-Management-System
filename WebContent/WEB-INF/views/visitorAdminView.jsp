<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Visitor Admin</title>
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="../resources/css/visitorViews.css">
	<script src="../resources/js/jquery.js"></script>
	<script src="../resources/js/bootstrap.js"></script>
	

</head>

<body class="container-fluid text-center">
	<header class="container text-center" id="title">
		<div class="ml15 pull-center">
			<h1>
				<span class="word">W </span> <span class="word">e </span> <span
					class="word">l </span> <span class="word">c </span> <span
					class="word">o </span> <span class="word">m </span> <span
					class="word welcome">e </span> <span class="word to">To </span> <span
					class="word ">P </span> <span class="word ">h </span> <span
					class="word ">o </span> <span class="word state">e </span> <span
					class="word ">n </span> <span class="word ">i </span> <span
					class="word ">x</span>
			</h1>
			<p class="word logo pull-center">
				<img src="../resources/images/logo.png" />
			</p>
		</div>
	</header>
	<section class="nav"></section>
	<section class="container">
		<div class="section_block">
			
				<table class="table">
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Phone</th>
						<th>Check In Time</th>
						<th>Check Out Time</th>						
						<th>Check Out</th>
					</tr>
					<c:forEach var="ba" items="${alldata}">
						<tr>
							<td>${ba.getFirstName()}</td>
							<td>${ba.getLastName()}</td>
							<td>${ba.getPhone()}</td>
							<td>${ba.getCheckedInTime()}</td>
							<td>${ba.getCheckedOutTime()}</td>
							<td><form class="admin-button"
									action="/Phoenix_Resource_Management_System/Visitor/AdminCO/${ba.getVisitorId()}">
									<button type="submit" class="admin-btn <c:if test="${ba.getHasCheckedOut()==1}"><c:out value = "disabled"/></c:if>"><h3>Check Out</h3></button>
								</form></td>
						</tr>
					</c:forEach>

				</table>
			
		</div>
	</section>
</body>
</html>