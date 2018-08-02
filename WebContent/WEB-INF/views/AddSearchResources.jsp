<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AddSearchResources</title>
</head>
<body>
<div class="container">	
	<form action="LocationResources/" method="post">
		<fieldset id="fieldset1">
			<legend>Location</legend>
			<div>
				<label>Location</label>
				<select name="location">
    			<c:forEach items="${listCategory}" var="loc">
    			<c:set var="locId" value="${fn:substring(loc, 0, 6)}" />
        		<option value="${locId}">${loc}</option>
    			</c:forEach>
				</select>
  			</div>
		</fieldset><br>
		<fieldset id="fieldset2">
			<legend>Resources</legend>
			<div>
				<label>Resources</label>
				<select name="resources">
				<option value="all">Select</option>
				<c:forEach items="${listRes}" var="res">
    			<c:set var="resTypeId" value="${fn:substring(res, 0, 4)}" />
        		<option value="${resTypeId}">${res}</option>
        		</c:forEach>
        		</select>
  			</div>
		</fieldset><br>
	<input type="submit" value="Search">
<!-- <a href="LocationResources/">Search</a><br> -->
		<fieldset>
		<hr>
			<div>
				<a href="#" class="btn btn-info btn-lg"><span class="glyphicon glyphicon-plus-sign"></span></a>
			</div>
		</fieldset>
	
	</form><br>
	
</div>

<h1>Resources</h1>


	<table border="3">
		<tr>
			<th>Type</th>
			<th>Resource Name</th>
			<th>Resource Type</th>
			
		</tr>
		<c:forEach var="allResources" items="${alldata}">
		<tr>
			<td>${allResources.getResourceId()}</td>
			<td>${allResources.getResourceName()}</td>
			<td>${allResources.getResourceTypeId()}</td>
			
			
		</tr>
		</c:forEach>
		</table>


</body>
</html>