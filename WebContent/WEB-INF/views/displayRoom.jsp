<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Your room</h1>
	<p id="roomName">Name: ${room.getResourceName()} </p>><br>
	Room #: ${room.getResourceRoomNumber()} <br>
	Description: ${room.getResourceDescription()} <br>
</body>
</html>