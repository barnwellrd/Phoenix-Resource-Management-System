<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="java.util.*, dbController.DBConnect" %>
<html lang="en">
	<head>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<%@ page import="java.sql.*" %>
		<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Chart</title>
		
		<!-- bootstrap -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<link href="css/style.css" rel="stylesheet" type="text/css"/>
			
		<!-- JQuery -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script> 
			$(function() {
				$('.drawArea').hide();
				$('#theButton').click(function() {
					$('.drawArea').show();
				});
				$('#viewType').change(function(){
					if ($( "#viewType" ).val() == "all") {
						$('#middle').hide(); 	// hide room list
					} else {
						$('#middle').show();
						var select = document.getElementById("roomType2");
						 var length = select.options.length;
						 for (i = 1; i < length; i++) {
						   select.options[i] = null;		// clear the roomType2 list
						 }
						 $("#roomType option").each(function(i){
							 	var resourceType = $( "#viewType" ).val();
							 	if($(this).attr('class') == resourceType) {
							 		$('#roomType2').append($('<option>', {	// add rooms based on the type to roomType2 dropdown
							 		    value: $(this).text(),
							 		    text: $(this).text()
							 		}));
							 	} 
						       // alert($(this).attr('class'));
						       // alert(resourceType);
						 });
					}
					//window.location="${context}/chart/"+this.value();
				});
				$('#dbConnect').hide();
				$('#period').change(function() {
					var type = $('#period').val();
					if (type == 'day') {
						$('#dateText').html('Date: ');
					} else {
						$('#dateText').html('Start date: ');
					}
				});
				var today = new Date();
				var dd = today.getDate();
				var mm = today.getMonth()+1; //January is 0!
				var yyyy = today.getFullYear();
				if(dd<10){
			        dd='0'+dd
			    } 
			    if(mm<10){
			        mm='0'+mm
			    } 
				
				today = yyyy+'-'+mm+'-'+dd;
				$('#datefield').attr("max",today);
			});
		</script>
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<style>
			#middle,#middle2 {
				display: none;
			}
			#header {
				border-bottom-style: double;
				margin-bottom: 50px;
			}
			#datefield {
				margin-top: 5px;
			}
			#theButton {
				margin-left: 15px;
			}
		</style>
	</head>
	<body>
		<script type="text/javascript">
		google.charts.load('current', {
			'packages' : [ 'corechart' ]
		});
	
		google.charts.setOnLoadCallback(drawChart);
	
		function drawChart() {
	
			//the google chart table
			var dataTable = new google.visualization.DataTable();
			
			//add the types of info we are looking at. 
			//room type and number of bookings in the room
			dataTable.addColumn('string', 'Room Type');
			dataTable.addColumn('number', 'Bookings');
			
			//the table that has our database output
			var outTable = document.getElementById("dbConnect");
			
			//the rows of the html table
			var rowsArr = outTable.rows;
			
			//add rowsArg.length empty rows to the google table
				//dataTable.addRows(rowsArr.length);
			dataTable.addRows(3);
					
			//first loop is the rows second is the columns.
	/*		for(var i=1; i<rowsArr.length; i++){
				for(var j=0; j<2; j++){
					dataTable.setCell(i,j,rowsArr[i].cells[j].textContent);
				}
			}
			*/
				for(var j=0; j<2; j++){
					dataTable.setCell(1,j,rowsArr[1].cells[j].textContent);
				}
				dataTable.setCell(2,0,"Not Used");
				dataTable.setCell(2,1,1-rowsArr[1].cells[1].textContent);
				
				var options = {
					title : 'Utilization Chart'
				};
		
				var chart = new google.visualization.PieChart(document
						.getElementById('chart_div'));
		
				chart.draw(dataTable, options);
			
			
		}
	</script>
		<div class="container">
			<div class="row">
				<div id="header" class="col">
					<h1 style="text-align: center;" >Room Utilization</h1>
				</div>
			</div>
			
			<form action="drawChart" method="post">
				<div class="row">
					<div class="col-sm">
						<div class="form-group">
							<label for="sel1">Select Type of Room:</label>
							<select class="form-control" id="viewType" name="viewType">
								<option value="all" selected>All Rooms</option>
				                <c:forEach items="${resourceTypes}" var="rt">
				                <c:set var="resourceTypeId" value="${fn:substring(rt, 0, 4)}" />
				                <option value="${resourceTypeId}">${rt}</option>
				                </c:forEach>
			                </select>
						</div> 
					</div>
					<div class="col-sm">
						<div id=middle class="form-group">
						<label>Select Room:</label>
							<select class="form-control" name="roomType2" id="roomType2">
				                <option value="all">All rooms</option>
				          	</select>
						</div>
					</div>
					<div class="col-sm">
						<div class="form-group">
							<label for="sel1">Select Utilization Period:</label>
							<select class="form-control" name="period" id="period">
								<option value="day">Day</option>
								<option value="weekly">Weekly</option>
								<option value="monthly">Monthly</option>
							</select>
						</div> 
					</div>
					<div class="col-sm">
					<!-- 	<div id="hide"><h2>hide this div</h2></div>  -->
						<label>Date: </label><br>
						<input id="datefield" type="date" name="pickedDate" required max='2000-13-13' />
					</div>
				</div>
				<div class="row">
					<input type="submit" class="btn btn-dark" id = "theButton"></input>
					<div id="middle2" class="form-group"> <!-- get the list of all rooms and save it in this dropdown -->
							<label for="sel1">Select room:</label>	<!-- this dropdown is hidden so the users can't see it. It's only used to filter another dropdown -->
							<select class="form-control" name="roomType" id="roomType">
				                <option value="all">All rooms</option>
				                <c:forEach items="${rooms}" var="room">
				                <c:set var="roomId" value="${fn:substring(room, 0, 4)}" />
				                <c:set var = "typeOfRoom" value="${fn:substring(room, 5, 9)}" />
				                <option value="${roomId}" class="${typeOfRoom}">${room}</option>
				                </c:forEach>
				          	</select>
						</div> 
				</div>
			</form>
			<div class="row" class="drawArea">
				<div id="chart_div" style="width: 700px; height: 540px;"></div>
			</div>
			<div class="row" class="drawArea">
				<%
					double utilNum =(double)session.getAttribute("util");
					if(utilNum >= 0) {
						String finalStr="";
						
						finalStr+="<table id='dbConnect' style='border:1px solid black'>";	
		
						//table headers row
						finalStr+="<tr>";
						
						finalStr+="<th style='border:1px solid black'>col1</th>";
						finalStr+="<th style='border:1px solid black'>col2</th>";
						
						//close table header row. 
						finalStr+="</tr>";
						//first row
						finalStr+="<tr>";
						finalStr+="<td>Used</td>";
						finalStr+="<td>";
						finalStr+=utilNum;
						finalStr+="</td>";
						finalStr+="</tr>";
						finalStr+="</table>";
						out.write(finalStr);	
					}
				%>
			</div>

		</div>
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	</body>
</html>