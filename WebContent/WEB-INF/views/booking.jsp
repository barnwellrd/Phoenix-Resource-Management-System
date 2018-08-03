<%@page import="rms.dao.*, rms.model.*, rms.services.*"%>
<%@page
	import="java.util.*, org.springframework.web.context.WebApplicationContext,
org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1">
<title>Booking</title>

<spring:url value="/resources/css/bootstrap.min.css" var="bootMin" />
<spring:url value="/resources/css/booking.css" var="bookingCSS" />
<spring:url value="/resources/css/daterangepicker.css"
	var="daterangepicker" />
<spring:url value="/resources/css/fullcalendar.css" var="fullCal" />

<link rel="stylesheet" href="${bootMin}" />
<link rel="stylesheet" href="${bookingCSS}" />
<link rel="stylesheet" href="${daterangepicker}" />
<link rel="stylesheet" href="${fullCal}" />

<spring:url value="/resources/js" var="JS" />

<script src="${JS}/jquery.js"></script>
<script src="${JS}/moment.min.js"></script>
<script src="${JS}/bootstrap.js"></script>
<script src="${JS}/daterangepicker.js"></script>
<script src="${JS}/fullcalendar.js"></script>

</head>

<body>

	<div class="container" id="allPage">

		<!-- Default Values provided. these are the id and name of the currently selected resource
			updated in the select event of the fullCalendar -->
		<p id="pageResourceId" style="display: none">1001</p>
		<p id="pageResourceName" style="display: none">SCRUM 1</p>

		<nav class="navbar navbar-default navbar-static-top">
			<div class="container-fluid">
				<a class="navbar-brand navbar-right" href="#"> <spring:url
						value="/resources/images" var="images" /> <img id="syntel-logo"
					src="${images}/syntel-logo.png" />

				</a>
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="dashboard"> <spring:url
							value="/resources/images" var="images" /> <img id="book-logo"
						src="${images}/book-logo.png" />
					</a>

				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li><a href="#">Account</a></li>
						<li><a href="#">Add A Resource</a></li>
						<li><a href="logout">Log Out</a></li>
					</ul>

				</div>
			</div>
		</nav>

		<div class="container" id="rows">

			<div class="row">
				<div class="col-lg-4">
					<div class="panel">
						<div class="panel-body">

							<iframe src="AddSearchResources"> </iframe>

						</div>
					</div>
					<!--Panel-->
				</div>
				<!--Col-->
				<div class="col-lg-8">
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

						<input class="form-control input-md" type="text" id="bookingId"
							name="bookingId" readonly="readonly" />
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
					<button type="button" data-dismiss="modal" class="btn btn-primary"
						id="editButton">Save Changes</button>

					<button type="button" data-dismiss="modal" class="btn btn-danger"
						id="deleteButton">Delete</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Add event modal for weekly view-->
	<div class="modal fade" id="addEventByWeekModal" tabindex="-1"
		role="dialog" aria-labelledby="eventModalLabel" aria-hidden="true">
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
					<!-- Room -->
					<div class="input-group">
						<div class="input-group-addon">
							<span class='glyphicon glyphicon-calendar'></span> <span
								class="input-group-text">Room</span>
						</div>
						<input class="form-control input-md" type="text" id="room"
							name="room" readonly="readonly" />
					</div>

					<!-- Date -->
					<div class="input-group">
						<div class="input-group-addon">
							<span class='glyphicon glyphicon-calendar'></span> <span
								class="input-group-text">Date</span>
						</div>
						<input class="form-control input-md" type="date"
							placeholder="DD-MM" id="date" name="date" required />
					</div>

					<!-- Start time -->
					<div class="input-group">
						<div class="input-group-addon">
							<span class='glyphicon glyphicon-time'></span> <span
								class="input-group-text">From</span>
						</div>
						<input class="form-control input-md" type="time"
							placeholder="HH:MM" id="timeFrom" name="timeFrom" required>
					</div>

					<!-- Stop time -->
					<div class="input-group">
						<div class="input-group-addon">
							<span class='glyphicon glyphicon-time'></span> <span
								class="input-group-text">To</span>
						</div>
						<input class="form-control input-md" type="time"
							placeholder="HH:MM" id="timeTo" name="timeTo" required>
					</div>

					<!-- Weekly repeats -->
					<div class="input-group">
						<div class="input-group-addon">
							<span class='glyphicon glyphicon-repeat'></span> <span
								class="input-group-text"> Weekly Repeats </span>
						</div>
						<input class="form-control input-md" type="number" id="weeklyRep"
							min="0" max="4" value="0" name="weeklyRep" required />
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
						<button type='button' data-dismiss="modal" class="btn btn-primary"
							id="addButton">Add Event</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Add event modal for daily view-->
	<div class="modal fade" id="addEventByDayModal" tabindex="-1"
		role="dialog" aria-labelledby="eventModalLabel" aria-hidden="true">
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
					<!-- Room -->
					<div class="input-group">
						<div class="input-group-addon">
							<span class='glyphicon glyphicon-calendar'></span> <span
								class="input-group-text">Room</span>
						</div>
						<input class="form-control input-md" type="text" id="room"
							name="room" readonly="readonly" />
					</div>

					<!-- Start time -->
					<div class="input-group">
						<div class="input-group-addon">
							<span class='glyphicon glyphicon-calendar'></span> <span
								class="input-group-text">Date</span>
						</div>
						<input class="form-control input-md" type="date"
							placeholder="DD-MM" id="date" name="date" required />
					</div>

					<!-- From time -->
					<div class="input-group">
						<div class="input-group-addon">
							<span class='glyphicon glyphicon-time'></span> <span
								class="input-group-text">From</span>
						</div>
						<input class="form-control input-md" type="time"
							placeholder="HH:MM" id="timeFrom" name="timeFrom" required>
					</div>

					<!-- To tme -->
					<div class="input-group">
						<div class="input-group-addon">
							<span class='glyphicon glyphicon-time'></span> <span
								class="input-group-text">To</span>
						</div>
						<input class="form-control input-md" type="time"
							placeholder="HH:MM" id="timeTo" name="timeTo" required>
					</div>

					<!-- Day repeats -->
					<div class="input-group">
						<div class="input-group-addon">
							<span class='glyphicon glyphicon-hand-right'></span> <span
								class="input-group-text"> Daily Repeats </span>
						</div>
						<div class="input-group-addon"
							style="background-color: white; border: none;">
							<input type="checkbox" id="M" value="Monday"> M
						</div>
						<div class="input-group-addon"
							style="background-color: white; border: none;">
							<input type="checkbox" id="Tu" value="Tuesday"> Tu
						</div>
						<div class="input-group-addon"
							style="background-color: white; border: none;">
							<input type="checkbox" id="W" value="Wednesday"> W
						</div>
						<div class="input-group-addon"
							style="background-color: white; border: none;">
							<input type="checkbox" id="Th" value="Thursday"> Th
						</div>
						<div class="input-group-addon"
							style="background-color: white; border: none;">
							<input type="checkbox" id="F" value="Friday"> F
						</div>
						<div class="input-group-addon"
							style="background-color: white; border: none;">
							<input type="checkbox" id="Sa" value="Saturday"> Sa
						</div>
						<div class="input-group-addon"
							style="background-color: white; border: none;">
							<input type="checkbox" id="Su" value="Sunday"> Su
						</div>
					</div>



					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
						<button type='button' data-dismiss="modal" class="btn btn-primary"
							id="addButton">Add Event</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
	var active = "view";

	$(document).ready(
	    function () {

			//the initial rendering of the full calendar. 
			$('#dispCal').fullCalendar(
					{
						// Limit calendar to show only two months from now
						validRange : function(currentDate) {
							return {
								start : currentDate.clone(),
								end : currentDate.clone().add(2,
										'months')
							};
						},
						header : {
							left : 'prev,next today',
							center : 'title',
							right : 'agendaWeek,agendaDay'
						},
						themeSystem : 'bootstrap3',
						minTime : "06:00:00",
						maxTime : "18:00:00",
						height : 500,
						defaultView : 'agendaWeek',
						selectable : false,
						selectConstraint : {
							start : moment().startOf('day'),
							end : moment().startOf('day').add(6,
									'months'),
						},
						eventMouseover: function(calEvent, jsEvent) {
						    var tooltip = '<div class="tooltipevent" style="background:white;width:150px;height:25px;position:absolute;z-index:10001;">' + "Click to edit or delete" + '</div>';
						    var $tooltip = $(tooltip).appendTo('body');

						    $(this).mouseover(function(e) {
						        $(this).css('z-index', 10000);
						        $tooltip.fadeIn('500');
						        $tooltip.fadeTo('10', 1.9);
						    }).mousemove(function(e) {
						        $tooltip.css('top', e.pageY + 10);
						        $tooltip.css('left', e.pageX + 20);
						    });
						},

						eventMouseout: function(calEvent, jsEvent) {
						    $(this).css('z-index', 8);
						    $('.tooltipevent').remove();
						},
						// Handle creation of an event
						select : function(startDate, endDate) {	
							
							// Find the room picked
							var name = $("#pageResourceName").val();									
							
							// Fill the views by form type
							var viewType = $('#dispCal').fullCalendar('getView').name;
							if(viewType === "agendaWeek"){
								// Fill in the date
								$("#addEventByWeekModal  #date").val($.fullCalendar.formatDate(startDate, "YYYY-MM-DD"));
								$("#addEventByWeekModal  #timeFrom").val(startDate.format("HH:mm"));
								$("#addEventByWeekModal  #timeTo").val(endDate.format("HH:mm"));
								$("#addEventByWeekModal  #room").val(name);
								$("#addEventByWeekModal").modal("show");
							} else if(viewType === "agendaDay"){
								$("#addEventByDayModal  #date").val($.fullCalendar.formatDate(startDate, "YYYY-MM-DD"));
								$("#addEventByDayModal  #timeFrom").val(startDate.format("HH:mm"));
								$("#addEventByDayModal  #timeTo").val(endDate.format("HH:mm"));	
								$("#addEventByDayModal").modal("show");	
								$("#addEventByDayModal  #room").val(name);
							}
						},
						eventClick : function(calEvent, jsEvent, view) {
							$("#editDate").val($.fullCalendar.formatDate(calEvent.start, "YYYY-MM-DD"));
							$("#editTimeFrom").val(calEvent.start.format("HH:mm"));
							$("#editTimeTo").val(calEvent.end.format("HH:mm"));
							$("#editRoom").val(calEvent.title);
							$("#bookingId").val(calEvent.id);
							$("#changeEventModal").modal('show');
						},
						eventLimit : true, // allow "more" link when too many events
					});//End fullCalendar initial render
			    	
			//on iframe load. 		
	      $('iframe').load(function () {

	    	  
	    	//for clicking on cards of resources.   
	        $("iframe").contents().find(".wrimagecard").on('click', function (event) {
	        	
	         //set all cards to not be highlighted.	
	         //need to set all children of the card or they will highlight individually
	          $("iframe").contents().find(".wrimagecard *").css('background-color', 'white');
	          
	          //set the clicked card to be highlighted
	          //have to set siblings children and parent bc there are multiple elements in the cards like the title and icon. 
	          //the user could have clicked any of the smaller elements. 
	          $(event.target).css('background-color', '#99ff99');
	          $(event.target.parentElement).css('background-color', '#99ff99');
	          $(event.target.children).css('background-color', '#99ff99');
	          $(event.target).siblings().css('background-color', '#99ff99');
	          
	          //need two in case user clicks on child element vs entire card. 
	          var possibleId = $(event.target.parentElement).contents().find(".filterResourceId").text();
	          var possibleId2 = $(event.target.parentElement).contents().siblings(".filterResourceId").text();

	          var resId = "";
	          
	          //get the id that was actually there. 
	          if(possibleId)
	        	  resId = possibleId;
	          else
	        	  resId = possibleId2;
	          
	          //need two in case user clicks on child element vs entire card. 
	          var possibleName = $(event.target.parentElement).contents().find(".filterResourceName").text();
	          var possibleName2 = $(event.target.parentElement).contents().siblings(".filterResourceName").text();

	          var name = "";
	          
	          //get the id that was actually there. 
	          if(possibleName)
	        	  name = possibleName;
	          else
	        	  name = possibleName2;
	          
	          //set the page resource Id attribute to be the selected id. 
			  $("#pageResourceId").val(resId);
			  $("#pageResourceName").val(name);
	          
	          //This ajax call filters the calendar by the exact resourceId that was just clicked by the user. 
	          $.ajax({
		              url: "getBookingsAsTableByResourceId",

		              dataType: 'html',

		              //pass the resource Id. 
		              data: {
		                "resourceId": resId
		              },

		              success: function (result) {
								            	  
		            	 //remove events from the calendar and rerender with the ajax result
		                $("#dispCal").fullCalendar('removeEvents');
		                calRender(result);

		              },

		              fail: function (result) {
		                console.log("Failed get all by type service");
		                console.log(result);
		              }
		            });

	          		//the user can now make selections on the calendar. 
		            $("#dispCal").fullCalendar('option', {
		              selectable: true
		            });
				
	        });

	        $(this).contents().find("input[name='type']").on('change', function (event) {

	            console.log("chose a type");
	            var resId = ($('iframe').contents().find("input[name='type']:checked").val());
	            console.log(resId);

	            $.ajax({
	              url: "getBookingsAsTableByType",

	              dataType: 'html',

	              data: {
	                "resourceTypeId": resId
	              },

	              success: function (result) {

	                $("#dispCal").fullCalendar('removeEvents');
	                calRender(result);


	              },

	              fail: function (result) {
	                console.log("Failed get all by type service");
	                console.log(result);
	              }
	            });

	            var resId = ($('iframe').contents().find("form").submit());


	          });
	      });

	    


	         
	    

	      $('iframe').load(function () {
	        $(this).contents().find("#backButton").on('click',
	          function (event) {

	            console.log("clicked go back");

	            $("#dispCal").fullCalendar('option', {
	              selectable: false
	            });
	            
	            $.ajax({
	              url: "getAllBookingsAsTable",
	              dataType: 'html',
	              success: function (result) {

	                $("#dispCal").fullCalendar("removeEvents");
	                calRender(result);

	              },

	              fail: function (result) {
	                console.log("Failed get all service");
	                console.log(result);
	              }
	            });



	          });
	      });

	      $("#deleteButton").click(function () {
	        var id = $("#bookingId").val();
	        console.log(id);

	        $.ajax({
	          url: "deleteEvent",
	          success: function (result) {
	            //remove the event from the calendar
	            $("#dispCal").fullCalendar('removeEvents', id);

	          },
	          fail: function (result) {
	            console.log(result);
	          },
	          data: {
	            "bookingId": id
	          }
	        });
	      });

	      $("#addEventByDayModal #addButton").click(
	        function () {
	          console.log("Adding from day...");

	          //get fields from the inputs inside the add modal
	          var date = $("#addEventByDayModal #date").val();
	          var timeTo = $("#addEventByDayModal #timeTo").val();
	          var timeFrom = $("#addEventByDayModal #timeFrom").val();
	          var title = $("#addEventByDayModal #room").val();
	          var resId = ($("iframe").contents().find(
	            "#resourceId").html());
	          var m = $("#addEventByDayModal #M").prop("checked");
	          var tu = $("#addEventByDayModal #Tu").prop("checked");
	          var w = $("#addEventByDayModal #W").prop("checked");
	          var th = $("#addEventByDayModal #Th").prop("checked");
	          var f = $("#addEventByDayModal #F").prop("checked");
	          var sa = $("#addEventByDayModal #Sa").prop("checked");
	          var su = $("#addEventByDayModal #Su").prop("checked");
	          var repeats = [m, tu, w, th, f, sa, su];

	          console.log(date);
	          console.log(timeTo);
	          console.log(timeFrom);
	          console.log(title);
	          console.log(resId);
	          console.log(repeats);

	          $.ajax({
	            url: "addEvent",
	            success: function (result) {
	              console.log(result);

	              //fields contains a bookings object comma seperated fields 
	              var fields = result.split(",");

	              var start = fields[4].trim().replace(
	                "bookedStartTime=", "");
	              var end = fields[5].trim().replace(
	                "bookedEndTime=", "");
	              start = start.replace(" ", "T");
	              end = end.replace(" ", "T");

	              var title = fields[6];

	              var title = fields[7];

	              var id = fields[8];

	              //change bg color depending on type of resource
	          if (title.toLowerCase().includes("scrum"))
	            newEvent[3] = "FireBrick";
	          else if (title.toLowerCase().includes("conference"))
	            newEvent[3] = "DarkCyan";
	          else if (title.toLowerCase().includes("board"))
	            newEvent[3] = "OrangeRed";
	          else if (title.toLowerCase().includes("rec"))
	            newEvent[3] = "Chocolate";
	          else if (title.toLowerCase().includes("train"))
	            newEvent[3] = "OliveDrab";
	          else if (title.toLowerCase().includes("break"))
	            newEvent[3] = "SlateBlue";

	              //create event object to place on the calendar
	              var newEvent = {
	                id: id,
	                title: title,
	                start: start,
	                end: end,
	                backgroundColor: backgroundColor
	              };

	              //put the event on the calendar.
	              $("#dispCal").fullCalendar(
	                'renderEvent', newEvent, true);

	            },
	            fail: function (result) {
	              console.log("Failed to add...");
	              console.log(result);
	            },
	            //passing date and time to the addEvent jsp file. 
	            data: {
	              "date": date,
	              "timeTo": timeTo,
	              "timeFrom": timeFrom,
	              "title": title,
	              "resourceId": resId,
	              "repeats": repeats
	            }
	          });

	        });

	      //When adding an event. 
	      $("#addEventByWeekModal #addButton").click(
	        function () {

	          console.log("Adding from week...");

	          //get fields from the inputs inside the add modal
	          var date = $("#date").val();
	          var timeTo = $("#timeTo").val();
	          var timeFrom = $("#timeFrom").val();
	          var title = $("#room").val();
	          var resId = $("#pageResourceId").val();
	          var repeats = $("#weeklyRep").val();

	          console.log(date);
	          console.log(timeTo);
	          console.log(timeFrom);
	          console.log(title);
	          console.log(resId);
	          console.log(repeats);

	          $.ajax({
	            url: "addEvent",
	            success: function () {
	              $.ajax({
	                url: "getBookingsAsTableByResourceId",
	                dataType: 'html',
	                success: function (result) {
	                	
		               $("#dispCal").fullCalendar('removeEvents');
	                  calRender(result);
	                },
					data:{
						"resourceId":resId
					},
	                eventLimit: true, // allow "more" link when too many events

	              });
	            },
				//passing date and time to the addEvent jsp file. 
				data : {
					"date" : date,
					"timeTo" : timeTo,
					"timeFrom" : timeFrom,
					"title" : title,
					"resourceId" : resId,
					"repeats": repeats
				}
	          });
	        });
	      
	  	$.ajax({
			  url: "getAllBookingsAsTable",
			  dataType: 'html',
			  success: function (result) {
			  	calRender(result);
			  },
			  fail: function (result) {
			    console.log("Failed get all service");
			    console.log(result);
			  }
		});
	      
	      
	      });


	
	      function calRender(result) {
	        var table = $.parseHTML(result)[0];

	        var formattedEventData = [];

	        var eventsArray = [];

	        console.log(table);

	        //create an array of event objects for the Calendar on the page. 
	        $(table).find("tr").each(function () {
	          var newEvent = [];

	          var start = this.cells[1].innerHTML;
	          start = start.replace(" ", "T");

	          var end = this.cells[2].innerHTML;
	          end = end.replace(" ", "T");

	          var title = this.cells[0].innerHTML;

	          var id = this.cells[3].innerHTML;

	          newEvent[0] = title;
	          newEvent[1] = start;
	          newEvent[2] = end;

	          if (title.toLowerCase().includes("scrum"))
	            newEvent[3] = "FireBrick";
	          else if (title.toLowerCase().includes("conference"))
	            newEvent[3] = "DarkCyan";
	          else if (title.toLowerCase().includes("board"))
	            newEvent[3] = "OrangeRed";
	          else if (title.toLowerCase().includes("recreation"))
	            newEvent[3] = "Chocolate";
	          else if (title.toLowerCase().includes("train"))
	            newEvent[3] = "OliveDrab";
	          else if (title.toLowerCase().includes("break"))
	            newEvent[3] = "SlateBlue";

	          newEvent[4] = id;
	          eventsArray.push(newEvent);
	        });

	        //place all the events into an array formatted for FullCalendar
	        for (var k = 0; k < eventsArray.length; k++) {
	          formattedEventData.push({
	            title: eventsArray[k][0],
	            start: eventsArray[k][1],
	            end: eventsArray[k][2],
	            backgroundColor: eventsArray[k][3],
	            id: eventsArray[k][4]
	          });
	        }

	        $("#dispCal").fullCalendar('renderEvents', formattedEventData, true);
	      }

	    
	      $("#view").css("background-color", "lightblue");
	
	</script>

</body>

</html>