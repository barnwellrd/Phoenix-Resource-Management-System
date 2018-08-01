<%@page import="rms.dao.*, rms.model.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1">
<title>Dashboard</title>

<spring:url value="/resources/css/bootstrap.min.css" var="bootMin" />
<spring:url value="/resources/css/booking.css" var="bookingCSS" />
<spring:url value="/resources/css/daterangepicker.css" var="daterangepicker" />
<spring:url value="/resources/css/fullcalendar.css" var="fullCal" />

<link rel="stylesheet" href="${bootMin}" />
<link rel="stylesheet" href="${bookingCSS}" />
<link rel="stylesheet" href="${daterangepicker}" />
<link rel="stylesheet" href="${fullCal}" />

<spring:url value="/resources/js/jquery.js" var="jquery" />
<spring:url value="/resources/js/moment.min.js" var="moment" />
<spring:url value="/resources/js/bootstrap.js" var="bootJS" />
<spring:url value="/resources/js/daterangepicker.js" var="dateRangeJS" />
<spring:url value="/resources/js/fullcalendar.js" var="fullCalJS" />

<script src="${jquery}"></script>
<script src="${moment}"></script>
<script src="${bootJS}"></script>
<script src="${dateRangeJS}"></script>
<script src="${fullCalJS}"></script>

</head>

<body>

	<div class="container" id="allPage">
	
		<nav class="navbar navbar-default navbar-static-top">
			<div class="container-fluid">
				<a class="navbar-brand navbar-right" href="#">
				
					<spring:url value="/resources/images" var="images" />
    				<img id="syntel-logo" src="${images}/syntel-logo.png"/>
				
				</a>
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"> 
						<spring:url value="/resources/images" var="images" />
  					  	<img id="book-logo" src="${images}/book-logo.png"/>
					</a>

				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li><a href="#">Make a Booking</a></li>
						<li><a href="#">Account</a></li>
						<li><a href="#">Add A Resource</a></li>
						<li><a href="login">Log Out</a></li>
					</ul>

				</div>
			</div>
		</nav>

		<div class="container" id="rows">

			<div class="row">

				<div class="col-lg-12">
					<div class="panel">

						<div class="panel-body">
							<div id="dispCal"></div>
						</div>
					</div>
					<!--Panel-->
				</div>
				<!--Col-->

			</div>
			<!--Row-->
		</div>

	</div>
	<!--All Page-->

	<!--Edit Event Modal -->
	<div class="modal fade" id="changeEventModal" tabindex="-1"
		role="dialog" aria-labelledby="eventModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Change Event</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
					<div class="modal-body" id="eventChangeBody">
						
						<div class="input-group">
							<div class="input-group-addon">
								<span class='glyphicon glyphicon-calendar'></span> <span
									class="input-group-text">Booking ID</span>
							</div>
						
							<input class="form-control input-md" type="text"
								id="bookingId" name="bookingId" readonly="readonly"/>
						</div>
						<div class="input-group">
							<div class="input-group-addon">
								<span class='glyphicon glyphicon-calendar'></span> <span
									class="input-group-text">Room</span>
							</div>
							<input class="form-control input-md" type="text" id="editRoom"
								name="editRoom" readonly="readonly" />
						</div>
						<div class="input-group">
							<div class="input-group-addon">
								<span class='glyphicon glyphicon-calendar'></span> <span
									class="input-group-text">From</span>
							</div>
							<input class="form-control input-md" type="text"
								placeholder="DD-MM" id="editDate" name="editDate" required />
						</div>
	

						<div class="input-group">
							<div class="input-group-addon">
								<span class='glyphicon glyphicon-time'></span> <span
									class="input-group-text">From</span>
							</div>
							<input class="form-control input-md" type="time"
								placeholder="HH:MM" id="editTimeFrom" name="editTimeFrom"
								required>
						</div>
	
						<div class="input-group">
							<div class="input-group-addon">
								<span class='glyphicon glyphicon-time'></span> <span
									class="input-group-text">To</span>
							</div>
							<input class="form-control input-md" type="time"
								placeholder="HH:MM" id="editTimeTo" name="editTimeTo" required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
							<button type="button" data-dismiss="modal" class="btn btn-danger" id="deleteButton">Delete</button>
					</div>
				
			</div>
		</div>
	</div>

	<!--Add Event Modal -->
	<div class="modal fade" id="addEventModal" tabindex="-1" role="dialog"
		aria-labelledby="eventModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addEventLabel">Add Event</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

					<div class="modal-body" id="eventAddBody">
						<div class="input-group">
							<div class="input-group-addon">
								<span class='glyphicon glyphicon-calendar'></span> <span
									class="input-group-text">Room</span>
							</div>
							<input class="form-control input-md" type="text" id="room"
								name="room" readonly="readonly" />
						</div>
						<div class="input-group">
							<div class="input-group-addon">
								<span class='glyphicon glyphicon-calendar'></span> <span
									class="input-group-text">From</span>
							</div>
							<input class="form-control input-md" type="text"
								placeholder="DD-MM" id="date" name="date" required />
						</div>

						<div class="input-group">
							<div class="input-group-addon">
								<span class='glyphicon glyphicon-time'></span> <span
									class="input-group-text">From</span>
							</div>
							<input class="form-control input-md" type="time"
								placeholder="HH:MM" id="timeFrom" name="timeFrom" required>
						</div>

						<div class="input-group">
							<div class="input-group-addon">
								<span class='glyphicon glyphicon-time'></span> <span
									class="input-group-text">To</span>
							</div>
							<input class="form-control input-md" type="time"
								placeholder="HH:MM" id="timeTo" name="timeTo" required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
						<button type='button' data-dismiss="modal" class="btn btn-primary" id="addButton">Add Event</button>
					</div>

			</div>
		</div>
	</div>

	<script>
		var active = "view";
		
		$(document).ready(function () {
	        	
		   	$("#deleteButton").click(function(){
				var id = $("#bookingId").val();
				console.log(id);
			    $.ajax({
			    	url:"deleteEvent", 
			    	success: function(result){
					    //remove the event from the calendar
					    $("#dispCal").fullCalendar( 'removeEvents',id );

			    	},
			    	fail: function(result){
			    		console.log(result);
			    	},
			   	 	data: {"bookingId": id}
			    });


			});
		   	
		   	//When adding an event. 
		   	$("#addButton").click(function(){
		   		
		   		console.log("Adding...");
		   		
		   		//get fields from the inputs inside the add modal
		   		var date= $("#date").val();
				var timeTo = $("#timeTo").val();
				var timeFrom = $("#timeFrom").val();
				
			    $.ajax({
			    	url:"addEvent", 
			    	success: function(result){
			    		console.log(result);
			    		
			    		//fields contains a bookings object comma seperated fields 
			    		var fields = result.split(",");
			    		
			    		var start = fields[4].trim().replace("bookedStartTime=","");
			    		var end = fields[5].trim().replace("bookedEndTime=","");	
						start = start.replace(" ","T");
						end = end.replace(" ","T");
			    		
						var title = fields[6];
						
			    		var id = fields[7];
			    		
						var backgroundColor= "green";
			    		
						//change bg color depending on type of resource
			        	if(title.includes("SCRUM"))
			        		backgroundColor= "Red";
			        	else if(title.includes("Conference"))
			        		backgroundColor = "Blue";			
			        	
						//create event object to place on the calendar
			            var newEvent = {
			            		id: id,
			                    title: title,
			                    start: start,
			                    end: end,
			                    backgroundColor: backgroundColor
			            };
			            
						//put the event on the calendar.
					    $("#dispCal").fullCalendar( 'renderEvent', newEvent ,true );
				        
			    	},
			    	fail: function(result){
				   		console.log("Failed to add...");
			    		console.log(result);
			    	},
			    	//passing date and time to the addEvent jsp file. 
			   	 	data: {"date": date, "timeTo": timeTo, "timeFrom":timeFrom}
			    });

			});
		   	
            $('#dispCal').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'agendaWeek,agendaDay'
                    },
                    themeSystem: 'bootstrap3',	                       
                    minTime: "06:00:00",
                    maxTime: "18:00:00",
                    height: 500,
                    defaultView: 'agendaWeek',
                    selectable: true,
                    selectConstraint: {
                        start: moment().startOf(
                            'day'),
                        end: moment().startOf(
                            'day').add(6,
                                'months'),
                    },
                    select: function (startDate, endDate) {
                        $("#roomName").html("Scrum 3");
                        $("#date").data('daterangepicker').setStartDate(startDate);
                        $("#date").data('daterangepicker').setEndDate(endDate);
                        $("#timeFrom").val(startDate.format("HH:mm"));
                        $("#timeTo").val(endDate.format("HH:mm"));
                        $("#addEventModal").modal("show");
                    },

                    eventMouseover: function (calEvent, js) {
                        var tooltip = '<div class="tooltipevent" style="padding:10px;width:250px;height:60px;background:black;position:absolute;z-index:10001;">'
                            + calEvent.title
                            + "<br/>"
                            + calEvent.start.format('MM/DD, h:mm a')
                            + " - "
                            + calEvent.end.format('MM/DD, h:mm a')
                            + '</div>';
                        $("body").append(tooltip);
                        $(this).mouseover(function(e) {
                            $(this).css('z-index', 10000);
                            $('.tooltipevent').fadeIn('500');
                            $('.tooltipevent').fadeTo('10', 1.9);
                        }).mousemove(function(e) {
                            $('.tooltipevent').css('top', e.clientY);
                            $('.tooltipevent').css('left', e.clientX);
                        });
                    },

                    eventMouseout: function ( calEvent, jsEvent) {
                        $(this).css('z-index', 8);
                        $('.tooltipevent').remove();
                    },

                    eventClick: function (calEvent,jsEvent, view) {

                        $("#editDate").data('daterangepicker').setStartDate(calEvent.start);
                        $("#editDate").data('daterangepicker').setEndDate(calEvent.end);
                        $("#editTimeFrom").val(calEvent.start.format("HH:mm"));
                        $("#editTimeTo").val(calEvent.end.format("HH:mm"));
                        $("#editRoom").val(calEvent.title);
						$("#bookingId").val(calEvent.id);
                        $("#changeEventModal").modal('show');

                    },

                    eventLimit: true, // allow "more" link when too many events

                });

    });

			$.ajax({
			    url:"getAllBookingsAsTable", 
			    dataType:'html',
			    success: function(result){
			    				    	
			    	var table = $.parseHTML(result)[0];
			    	
		        	var formattedEventData = [];

			    	var eventsArray = [];
			 
			    	console.log(table);
			    	
		        	//create an array of event objects for the Calendar on the page. 
		        	$(table).find("tr").each(function() {
			        	var newEvent = [];
			        	
		        		var start = this.cells[1].innerHTML;
		        		start = start.replace(" ","T");
		        		
		        		var end = this.cells[2].innerHTML;
		        		end = end.replace(" ","T");

		        		var title = this.cells[0].innerHTML;
		        		
		        		var id = this.cells[3].innerHTML;
		        		
		            	newEvent[0] = title;
			        	newEvent[1] = start;
			        	newEvent[2] = end;
			        	if(title.includes("SCRUM"))
			        		newEvent[3] = "Red";
			        	else if(title.includes("Conference"))
			        		newEvent[3] = "Blue";
			        	
			        	newEvent[4] = id;
			        	eventsArray.push(newEvent);
		        	});
		        	
		        	//place all the events into an array formatted for FullCalendar
			        for (var k = 0; k < eventsArray.length; k++) {
			       		formattedEventData.push({
			                title: eventsArray[k][0],
			       	        start: eventsArray[k][1],
			      	        end:   eventsArray[k][2],
			      	        backgroundColor: eventsArray[k][3],
			      	        id: eventsArray[k][4]
			       	    });
			        }
		        	
		        	$("#dispCal").fullCalendar( 'renderEvents', formattedEventData , true);
		        	
			    },
			    
			    fail: function(result){
			    	console.log("Failed get all service");
			    	console.log(result);
			    }
			});
	        	
	            $('input[name="date"]').daterangepicker({
	                minDate: moment().startOf('hour'),
	                alwaysShowCalendars: true,
	                locale: {
	                    format: 'YY/MM/DD'
	                }
	            });

	            $('input[name="editDate"]').daterangepicker({
	                minDate: moment().startOf('hour'),
	                alwaysShowCalendars: true,
	                locale: {
	                    format: 'YY/MM/DD'
	                }
	            });

	            $("#view").css("background-color", "lightblue");


		
	</script>

</body>

</html>