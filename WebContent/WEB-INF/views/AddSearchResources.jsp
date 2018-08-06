<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.sql.*"%>
<%
	ResultSet resultset = null;
%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.modal-dialog {
	border-radius: 25px;
	border-color: darkgreen;
	text-align: center;
	width: 370px;
}

.modal-header {
	background-color: green;
	color: white;
	text-align: center;
}

.modal-body {
	border-radius: 25px;
}

.resInput input, #resssTypeId, #resLoc {
	text-align: center;
	width: 250px;
	height: 35px;
}

input[type=number] {
	text-align: center;
}

select {
	text-align-last: center;
}

input:focus::-webkit-input-placeholder {
	color: transparent;
}

td {
	padding: 20px;
}

.checks label:hover {
	background-color: lightgray;
}
</style>
<meta name="viewport" http-equiv="Content-Type"
	content="text/html; charset=ISO-8859-1 width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css">

<spring:url value="/resources/css/showAll.css" var="bootMin" />
<link rel="stylesheet" href="${bootMin}" />


<spring:url value="/resources/css/dashboard.css" var="DashboardCSS" />
<link rel="stylesheet" href="${DashboardCSS}" />

<spring:url value="/resources/css/bootstrap.min.css" var="bootstrap" />
<link rel="stylesheet" href="${bootstrap}" />


<spring:url value="/resources/js" var="JS" />

<script src="${JS}/jquery.js"></script>
<script src="${JS}/bootstrap.js"></script>

<script>
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
	});
</script>

<title>AddSearchResources</title>
</head>
<body>
	<div class="container">

		<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<img src="resources/images/syntrans.png" alt="logo"
					style="height: 30%; width: 20%; padding-top: 0px; padding-bottom: 10px;">

				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>


			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-center"
					style="left-padding: 300px;">



				</ul>

				<ul class="nav navbar-nav navbar-right">

					<li style="font-size: 22px;"><a href="#"><span
							class="glyphicon glyphicon-user"></span> </a></li>

					<li style="font-size: 22px;"><a href="#"><span
							class="glyphicon glyphicon-bell"></span> </a></li>

					<li style="font-size: 22px;"><a href="#"><span
							class="glyphicon glyphicon-search"></span> </a></li>


				</ul>
			</div>
		</div>
	</div>
	</nav>

	<div class="container text-center">
		<div class="row Content2"
			style="background-image: url(/Phoenix_Resource_Management_System/resources/images/green.jpg);">
			<div class="col-sm-3 sidenav" id="resourcesidnav" >
				<span id="linkside" style=color:white;>
				<br>
				<br>
				<br>
					<form action="LocationResources/" method="post">
						<fieldset id="fieldset1">
							<h2>Location</h2>
							<div>
								<label>Location</label> <select name="location">
									<c:forEach items="${listCategory}" var="loc">
										<c:set var="locId" value="${fn:substring(loc, 0, 6)}" />
										<option value="${locId}">${loc}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<br>
						<br>
						<fieldset id="fieldset2">
							<h2>Resources</h2>
							<div>
								<label>Resources</label> <select name="resources">
									<option value="all">Select</option>
									<c:forEach items="${listRes}" var="res">
										<c:set var="resTypeId" value="${fn:substring(res, 0, 4)}" />
										<c:set var="resTypeName"
											value="${fn:substringAfter(res, resTypeId)}" />
										<option value="${resTypeId}">${resTypeName}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<br> <input type="submit" value="Search">
						<!-- <a href="LocationResources/">Search</a><br> -->
					</form> <br>
					<button type="button" class="btn btn-success btn-sm"
						data-toggle="modal" data-target="#myModal"
						style="padding-left: 10px;">
						Add Resource <br> <span class="glyphicon glyphicon-plus-sign"
							style="font-size: 20px; text-align: center;"></span> <br>
					</button>

				</span>
			</div>


			<div class="col-sm-8 text-left">
				<br>
				<!--
 <div class="well well-lg" style="background-color:rgb(0,50,0); color:white;"><h1>   Resource Booker </h1> </div> -->
				<div class="help"
					style="background-color: rgb(0, 50, 0); border-radius: 25px;">
					<center>
						<img src="resources/images/rms.png" alt="rmsLogo"
							style="width: 300px;">

					</center>
				</div>

				<div class="resource-container">
					<div class="row">
						<div class="here">
								<br>

								<c:forEach var="allResources" items="${alldata}">
									<c:if
										test="${fn:containsIgnoreCase(allResources.getResourceName(),'scrum')}">
										<div class="col-xs-6 col-sm-2">
											<div class="wrimagecard wrimagecard-topimage">
												<div class="wrimagecard-topimage_header">
													<h4>${allResources.getResourceName()}</h4>
													<i class="fa fa-list" style="color: #267326"></i>
												</div>
											</div>
										</div>
									</c:if>
									<c:if
										test="${fn:containsIgnoreCase(allResources.getResourceName(), 'conference')}">
										<div class="col-xs-6 col-sm-2">
											<div class="wrimagecard wrimagecard-topimage">
												<div class="wrimagecard-topimage_header">
													<h4>${allResources.getResourceName()}</h4>
													<i class="fa fa-cubes" style="color: #267326"></i>
												</div>
											</div>
										</div>
									</c:if>
									<c:if
										test="${fn:containsIgnoreCase(allResources.getResourceName(), 'training')}">
										<div class="col-xs-6 col-sm-2">
											<div class="wrimagecard wrimagecard-topimage">
												<div class="wrimagecard-topimage_header">
													<h4>${allResources.getResourceName()}</h4>
													<i class="fa fa-pencil-square-o" style="color: #267326"></i>
												</div>
											</div>
										</div>
									</c:if>
									<c:if
										test="${fn:containsIgnoreCase(allResources.getResourceName(), 'board')}">
										<div class="col-xs-6 col-sm-2">
											<div class="wrimagecard wrimagecard-topimage">
												<div class="wrimagecard-topimage_header">
													<h4>${allResources.getResourceName()}</h4>
													<i class="fa fa-users" style="color: #267326"></i>
												</div>
											</div>
										</div>
									</c:if>
									<c:if
										test="${fn:containsIgnoreCase(allResources.getResourceName(), 'break')}">
										<div class="col-xs-6 col-sm-2">
											<div class="wrimagecard wrimagecard-topimage">
												<div class="wrimagecard-topimage_header">
													<h4>${allResources.getResourceName()}</h4>
													<i class="fa fa-coffee" style="color: #267326"></i>
												</div>
											</div>
										</div>
									</c:if>
									<c:if
										test="${fn:containsIgnoreCase(allResources.getResourceName(), 're-creation')}">
										<div class="col-xs-6 col-sm-2">
											<div class="wrimagecard wrimagecard-topimage">
												<div class="wrimagecard-topimage_header">
													<h4>${allResources.getResourceName()}</h4>
													<i class="fa fa-backward" style="color: #267326"></i>
												</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
						</div>
					</div>
				</div>

				<div class="modal fade" id="myModal" role="dialog"
					style="border-radius: 25px;">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Resource Manager</h4>
							</div>

							<div class="modal-body">

								<form action="insertResource" method="post">
									<div class="resInput">

										<select name="location" required>
											<option value="" disabled selected>Select a Location</option>
											<c:forEach items="${listCategory}" var="loc">
												<c:set var="locId" value="${fn:substring(loc, 0, 6)}" />
												<option value="${locId}">${loc}</option>
											</c:forEach>
										</select> <br> <br> <select name="resources" required>
											<option value="" disabled selected>Select a Resource</option>
											<c:forEach items="${listRes}" var="res">
												<c:set var="resTypeId" value="${fn:substring(res, 0, 4)}" />
												<c:set var="resTypeName"
													value="${fn:substringAfter(res, resTypeId)}" />
												<option value="${resTypeId}">${resTypeName}</option>
											</c:forEach>
										</select> <br> <br> <input type="text" id="roomNum"
											name="roomNum" placeholder="Room Number" required></input> <br>
										<br> <input type="number" min="1" id="capacity"
											name="capacity" placeholder="Capacity" required />

									</div>
									<br> <label>Features:</label><br>
									<div class="checks">
										<table align="center">
											<tr>
												<td><label for="numResProj" data-toggle="tooltip"
													title="Projector"><span
														class="glyphicon glyphicon-film" style="font-size: 40px;"><span></span></span></label>
													<br> <input id="numResProj" value="0" type="number"
													min="0" name="numResProjName"
													style="width: 55px; height: 35px;" required /></td>
												<td><label for="numResPrint" data-toggle="tooltip"
													title="Printer"><span
														class="glyphicon glyphicon-print" style="font-size: 40px"><span></span></span></label>
													<br> <input id="numResPrint" value="0" type="number"
													min="0" name="numResPrintName"
													style="width: 55px; height: 35px;" required /></td>
												<td><label for="numResVid" data-toggle="tooltip"
													title="Video Conference Camera"><span
														class="glyphicon glyphicon-facetime-video"
														style="font-size: 40px"><span></span></span></label> <br> <input
													id="numResVid" value="0" type="number" min="0"
													name="numResVidName" style="width: 55px; height: 35px;"
													required /></td>
											</tr>
											<tr>
												<td><label for="numResTV" data-toggle="tooltip"
													title="TV"><span
														class="glyphicon glyphicon-blackboard"
														style="font-size: 40px"><span></span></span></label> <br> <input
													id="numResTV" value="0" type="number" min="0"
													name="numResTVName" style="width: 55px; height: 35px;"
													required /></td>
												<td><label for="numResWhiteBoard" data-toggle="tooltip"
													title="White Board"><span
														class="glyphicon glyphicon-pencil" style="font-size: 40px"><span></span></span></label>
													<br> <input id="numResWhiteBoard" value="0"
													type="number" min="0" name="numResWhiteBoardName"
													style="width: 55px; height: 35px;" required /></td>
												<td><label for="numResFood" data-toggle="tooltip"
													title="Food/Utensils"><span
														class="glyphicon glyphicon-cutlery"
														style="font-size: 40px"><span></span></span></label> <br> <input
													id="numResFood" value="0" type="number" min="0"
													name="numResFoodName" style="width: 55px; height: 35px;"
													required /></td>
											</tr>
										</table>
									</div>
									<table align="center">
										<tr>
											<td><label>Super Room: </label> <input type="hidden"
												value="0" name="isSuperRoom" /> <input type="checkbox"
												value="1" name="isSuperRoom" /> <span class="checkmark"></span>
											</td>
										</tr>
									</table>

									<label>Description:</label> <br>
									<textarea id="desc" name="desc"
										style="width: 250px; height: 80px;" required></textarea>
									<div class="modal-footer">
										<input type="submit" value="Submit" />
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
		<footer class="footer" id="footer"">
		<center>
			<p>� 2018 Syntel, Inc</p>
		</center>

		</footer>

	</div>
</body>
</body>
</html>