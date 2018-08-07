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

<spring:url value="/resources/css/showAll.css" var="bootMin" />
<link rel="stylesheet" href="${bootMin}" />

 
<spring:url value="/resources/css/bootstrap.css" var="bootstrap" />
<link rel="stylesheet" href="${bootstrap}" />


<spring:url value="/resources/js" var="JS"/>

<script src = "${JS}/jquery.js"></script>
<script src = "${JS}/bootstrap.js"></script>


<title>Insert title here</title>
</head>
<body>
	<h4 style="text-align: center">${
		alldata.get(0).getResourceName().substring(0,alldata.get(0).getResourceName().length()-2)
	}
		Rooms</h4>

	<div class="container">
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<br>

				<c:forEach var="allResources" items="${alldata}">
					<div class="col-xs-6 col-sm-4">
						<div class="wrimagecard wrimagecard-topimage dropdown">
							<div class="wrimagecard-topimage_header">
								<h4 class="filterResourceName">${allResources.getResourceName()}</h4>
								<!-- Hidden tag that will provide the resource Id on the page -->
								<p style="display: none" class="filterResourceId">${allResources.getResourceId()}</p>
								
								<div class="dropdown-content">
									<table>
										
										<spring:url value="/resources/images" var="images" /> 
										
										<!-- Loops through all features to choose which belong to this resource -->
										<c:forEach var="feat" items="${featData}">
											<c:if
												test="${fn:containsIgnoreCase(feat.getResourceName(),allResources.getResourceName())}">

												<c:if
													test="${fn:containsIgnoreCase(feat.getFeatureName(), 'Projector')}">
													<tr>
														<td><img class="irc_mi" alt="Image result for projector icon png" src="${images}/projector.png" />
														</td>
														<td>${feat.getQuantity()}</td>
													</tr>
												</c:if>

												<c:if
													test="${fn:containsIgnoreCase(feat.getFeatureName(), 'Desktop')}">
													<tr>
														<td><img class="irc_mi"
															src="${images}/desktop.jpg"
															alt="Image result for tv icon png"></td>
														<td>${feat.getQuantity()}</td>
													</tr>
												</c:if>

												<c:if
													test="${fn:containsIgnoreCase(feat.getFeatureName(), 'Chair')}">
													<tr>
														<td><img class="irc_mi"
															src="${images}/chair.png"
															alt="Chair icon"></td>
														<td>${feat.getQuantity()}</td>
													</tr>
												</c:if>
											
												<c:if
													test="${fn:containsIgnoreCase(feat.getFeatureName(), 'Printer')}">
													<tr>
														<td><img class="irc_mi"
															src="${images}/printer.jpg"
															alt="Image result for printer icon png"></td>
														<td>${feat.getQuantity()}</td>
													</tr>
												</c:if>

												<c:if
													test="${fn:containsIgnoreCase(feat.getFeatureName(), 'Whiteboard')}">
													<tr>
														<td><img class="irc_mi"
															src="${images}/whiteboard.png"
															alt="Image result for whiteboard icon png"></td>
														<td>${feat.getQuantity()}</td>
													</tr>
												</c:if>
												
																								<c:if
													test="${fn:containsIgnoreCase(feat.getFeatureName(), 'TV')}">
													<tr>
														<td><img class="irc_mi"
															src="${images}/tv.jpg"
															alt="Image result for TV icon png"></td>
														<td>${feat.getQuantity()}</td>
													</tr>
												</c:if>
											</c:if>
										</c:forEach>
									</table>
								</div>
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
									test="${fn:containsIgnoreCase(allResources.getResourceName(), 're-creation')}">
									<i class="fa fa-child" style="color: #267326"></i>

								</c:if>
							</div>
						</div>
					</div>

				</c:forEach>
			</div>
		</div>

		<form action="showAllResources" style="text-align: center">
			<button id="backButton" type="submit">Show All</button>
		</form>
	</div>



</body>
</html>
