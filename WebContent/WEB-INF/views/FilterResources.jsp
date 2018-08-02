<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Resources for </h1>

<form>
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
	</form>

</body>
</html>