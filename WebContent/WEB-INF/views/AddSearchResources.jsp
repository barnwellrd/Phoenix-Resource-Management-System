<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.sql.*"%>
<%
	ResultSet resultset = null;
%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta name="viewport" http-equiv="Content-Type"
	content="text/html; charset=ISO-8859-1 width=device-width, initial-scale=1">
	

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css">

<spring:url value="/resources/css/mycss.css" var="bootMin" />
<link rel="stylesheet" href="${bootMin}" />

<spring:url value="/resources/css/all.css" var="awesome" />
<link rel="stylesheet" href="${awesome}" />
 
<spring:url value="/resources/css/bootstrap.css" var="bootstrap" />
<link rel="stylesheet" href="${bootstrap}" />


<spring:url value="/resources/js" var="JS"/>

<script src = "${JS}/jquery.js"></script>
<script src = "${JS}/bootstrap.js"></script>
<title>AddSearchResources</title>
</head>
<body>
	<div class="container">
		<form action="LocationResources/" method="post">
			<fieldset id="fieldset1">
				<legend>Location</legend>
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
			<fieldset id="fieldset2">
				<legend>Resources</legend>
				<div>
					<label>Resources</label> <select name="resources">
						<option value="all">Select</option>
						<c:forEach items="${listRes}" var="res">
							<c:set var="resTypeId" value="${fn:substring(res, 0, 4)}" />
							<c:set var="resTypeName" value="${fn:substringAfter(res, resTypeId)}" />
							<option value="${resTypeId}">${resTypeName}</option>
						</c:forEach>
					</select>
				</div>
			</fieldset>
			<br> <input type="submit" value="Search">
			<!-- <a href="LocationResources/">Search</a><br> -->
		</form>
		<br>

	</div>

	<h1>Resources</h1>


	<div class="container">
				<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<div class="panel panel-default bootcards-summary">
					<br>

					<c:forEach var="allResources" items="${alldata}">
						<div class="col-xs-6 col-sm-4">
							<div class="wrimagecard wrimagecard-topimage">
								<div class="wrimagecard-topimage_header">
									<h4>${allResources.getResourceName()}</h4>
									<!-- Hidden tag that will provide the resource Id on the page -->
									<p style="display:none" class="filterResourceId">${allResources.getResourceId()}</p>
									<c:if
										test="${fn:containsIgnoreCase(allResources.getResourceName(),'scrum')}">
										<i class="fa fa-list" style="color: #267326"></i>
									</c:if>
									<c:if
										test="${fn:containsIgnoreCase(allResources.getResourceName(), 'conference')}">
										<i class="fa fa-cubes" style="color: #267326"></i>
									</c:if>
									<c:if
										test="${fn:containsIgnoreCase(allResources.getResourceName(), 'training')}">
										<i class="fa fa-pen-square" style="color: #267326"></i>
									</c:if>
									<c:if
										test="${fn:containsIgnoreCase(allResources.getResourceName(), 'board')}">
										<i class="fa fa-users" style="color: #267326"></i>
									</c:if>
									<c:if
										test="${fn:containsIgnoreCase(allResources.getResourceName(), 'break')}">
										<i class="fa fa-coffee" style="color: #267326"></i>
									</c:if>
									<c:if
										test="${fn:containsIgnoreCase(allResources.getResourceName(), 'recreation')}">
										<i class="fas fa-circle-notch" style="color: #267326"></i>

									</c:if>
								</div>
							</div>
						</div>

					</c:forEach>
				</div>
			</div>
		</div>
	</div>


</body>
</html>