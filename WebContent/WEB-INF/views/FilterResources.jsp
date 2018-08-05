<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" http-equiv="Content-Type"
	content="text/html; charset=ISO-8859-1 width=device-width, initial-scale=1">
	
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css">

<spring:url value="/resources/css/mycss.css" var="bootMin" />
<link rel="stylesheet" href="${bootMin}" />

 
<spring:url value="/resources/css/bootstrap.css" var="bootstrap" />
<link rel="stylesheet" href="${bootstrap}" />


<spring:url value="/resources/js" var="JS"/>

<script src = "${JS}/jquery.js"></script>
<script src = "${JS}/bootstrap.js"></script>


<title>Insert title here</title>
</head>
<body>
	<h1>Resources for</h1>

	<div class="container">
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<div class="panel panel-default bootcards-summary">
					<br>

					<c:forEach var="allResources" items="${alldata}">
						<c:if test="${fn:containsIgnoreCase(allResources.getResourceName(),'scrum')}">
							<div class="col-xs-6 col-sm-4">
								<div class="wrimagecard wrimagecard-topimage">
									<div class="wrimagecard-topimage_header">
										<h4>${allResources.getResourceName()}</h4>
										<i class="fa fa-list" style="color: #267326"></i>
									</div>
								</div>
							</div>
						</c:if>
							<c:if test="${fn:containsIgnoreCase(allResources.getResourceName(), 'conference')}">
							<div class="col-xs-6 col-sm-4">
								<div class="wrimagecard wrimagecard-topimage">
									<div class="wrimagecard-topimage_header">
										<h4>${allResources.getResourceName()}</h4>
										<i class="fa fa-cubes" style="color: #267326"></i>
									</div>
								</div>
							</div>
						</c:if>
							<c:if test="${fn:containsIgnoreCase(allResources.getResourceName(), 'training')}">
							<div class="col-xs-6 col-sm-4">
						<div class="wrimagecard wrimagecard-topimage">
							<div class="wrimagecard-topimage_header"> <h4>${allResources.getResourceName()}</h4>
								<i class="fa fa-pencil-square-o" style="color: #267326"></i>
							</div>
						</div>
					</div>
						</c:if>
							<c:if test="${fn:containsIgnoreCase(allResources.getResourceName(), 'board')}">
							<div class="col-xs-6 col-sm-4">
								<div class="wrimagecard wrimagecard-topimage">
									<div class="wrimagecard-topimage_header">
										<h4>${allResources.getResourceName()}</h4>
										<i class="fa fa-users" style="color: #267326"></i>
									</div>
								</div>
							</div>
						</c:if>
							<c:if test="${fn:containsIgnoreCase(allResources.getResourceName(), 'break')}">
							<div class="col-xs-6 col-sm-4">
								<div class="wrimagecard wrimagecard-topimage">
									<div class="wrimagecard-topimage_header">
										<h4>${allResources.getResourceName()}</h4>
										<i class="fa fa-coffee" style="color: #267326"></i>
									</div>
								</div>
							</div>
						</c:if>
							<c:if test="${fn:containsIgnoreCase(allResources.getResourceName(), 're-creation')}">
							<div class="col-xs-6 col-sm-4">
								<div class="wrimagecard wrimagecard-topimage">
									<div class="wrimagecard-topimage_header">
										<h4>${allResources.getResourceName()}</h4>
										<i class="fa-circle-notch" style="color: #267326"></i>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>



</body>
</html>
