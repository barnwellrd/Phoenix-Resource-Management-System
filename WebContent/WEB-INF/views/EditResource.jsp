<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<title>Resource Edit</title>
</head>
<body>
<form action="../UpdateResource/${resourceId}" >
	<div class="form-group row" id="resourceForm">
		<div class="col-sm-4">
			<div class="">
			<label for="resource">Resource Type:</label>
			<select id="resource" name="resourceType" class="form-control">
			<c:forEach var="resourceType" items="${resourceTypes}">
				<c:choose>
					<c:when test="${resourceType.getResourceTypeId()==resource.getResourceTypeId()}">
						<option value="${resourceType}" selected="selected">${resourceType.getResourceTypeName()}</option>
					</c:when>
					<c:otherwise>
						<option value="${resourceType.getResourceTypeId()}">${resourceType.getResourceTypeName()}</option>
					</c:otherwise>
				</c:choose>	
			</c:forEach>
			</select>
			</div>
		</div>
	</div>
	<div class="form-group row">
		<div class=" col-sm-3">Feature Type</div>
		<div class="col-sm-2">Quantity </div>
		<div class="col-sm-2">Remove Feature</div>
	</div>
	<div id="featureList">
	<div class="row"></div>
	<c:forEach var="feature" items="${features}">
		<div class="form-group row">
		<div class="col-sm-3">
			<select name="featureType" class="form-control">
			<c:forEach var="fType" items="${featureTypes}">
				<c:choose>
					<c:when test="${feature.getFeatureTypeId()==fType.getFeatureTypeId()}">
						<option value="${fType.getFeatureTypeId()}" selected="selected">${fType.getFeatureTypeName()}</option>
					</c:when>
					<c:otherwise>
						<option value="${fType.getFeatureTypeId()}">${fType.getFeatureTypeName()}</option>
					</c:otherwise>
				</c:choose>	
			</c:forEach>
			</select>
		</div>
		<div class="col-sm-2"><input name="quantity" class="form-control" type="number" required/>${feature.getQuantity()} </div>
		<div class="col-sm-2"> <button type="button" class="btn btn-danger" id="removeButton">
				<span class="glyphicon glyphicon-remove"></span>
			</button>
		</div>
		</div>
	</c:forEach>
	</div>
	<button class="btn btn-secondary btn-small" type="button" onclick="addRow()"><span class="glyphicon glyphicon-plus"></span></button>
	<div class = "form-group">
		<label for="description">Description</label>
		<textarea class="form-control" rows="5" id="description" name="description">${resource.getDescription() }</textarea>
	</div>
	<div class="form-group">
		<label for="capacity">Capacity</label>
		<input class="form-control" type="number" name="capacity" required>${resrouce.getCapacity()}</input> 
	</div>
	<div class="form-check">
	<c:choose>
	<c:when test="${resource.getIsSuperRoom()}">
		<input class="form-check-input" name="superRoom" id="superRoom" type="checkbox" checked/> 
	</c:when>
	<c:otherwise>	
		<input class="form-check-input"name="superRoom" id="superRoom" type="checkbox"/>
	</c:otherwise>
	</c:choose>
	<label for = "superRoom">Super Room</label>
	</div>
	
		<div class="col-sm-2 pull-right">
			<button type="submit" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="Save changes to the Database.">
				<span class= "glyphicon glyphicon-floppy-disk"></span>
			</button>
			<button type="button" class="btn btn-danger " data-toggle="tooltip" data-placement="top" title="Delete Resource from Database" onclick="deleteConfirmation()">
				<span class= "glyphicon glyphicon-trash" ></span>
			</button>
		</div>
</form>
<script>
	function addRow(){
		$("#featureList .row:last").after('<div class="form-group row">'+
				'<div class="col-sm-3">'+
				'<select name="featureType" class="form-control">'+
				'<c:forEach var="featureType" items="${featureTypes }">'+
				'<option value="${featureType.getFeatureTypeId()}">${featureType.getFeatureTypeId()}</option>'+
				'</c:forEach>'+
				'</select></div>'+
				'<div class="col-sm-2"><input type="number" class="form-control" name="quantity" value="0" required/>'+
				' </div>'+
				'<div class="col-sm-1"> <button type="button" class="btn btn-danger" id="removeButton"><span class="glyphicon glyphicon-remove"></span></button>'+
				'</div></div>');
	}
	$('#featureList').on("click", ".row #removeButton", function(){
		$(this).closest('.row').remove();
	});
	$(document).ready(function(){
	    $('[data-toggle="tooltip"]').tooltip();   
	});
	function deleteConfirmation(){
		var ans = confirm("Delete this Resource?");
		if(ans)
			window.location.replace("../DeleteResource/${resourceId}");
	}
</script>
</body>
</html>