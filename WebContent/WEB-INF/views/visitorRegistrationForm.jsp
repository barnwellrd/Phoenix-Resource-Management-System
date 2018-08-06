<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html>
<html>
<head>
	<title>Register Now!</title>
	<link rel="stylesheet" href="../resources/fontawesome/css/all.css">
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="../resources/css/visitorRegistrationForm.css">
	<script src="../resources/js/jquery.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/jquery.js"></script>
	<!-- script src="../resources/js/visitor30SecRedirect.js"></script-->
	
</head>

<body class="container"  onload="startTime()">
	<header class="text-center">
		
		 <div class="ml15 pull-center">
				  
				  <h1>
					  <span class="word">W </span> 
					  <span class="word">e </span> 
					  <span class="word">l </span>
					  <span class="word">c </span>
					  <span class="word">o </span>
					  <span class="word">m </span>
					  <span class="word welcome">e </span>
					  <span class="word to">To </span>
					  <span class="word ">P </span> 
					  <span class="word ">h </span>
					  <span class="word ">o </span>
					  <span class="word state">e </span>
					  <span class="word ">n </span>
					  <span class="word ">i </span>
					  <span class="word ">x</span>
					  
					  <span id="timer" class="pull-right">
				  	  	
				 	  </span>
				  </h1>
				  
				  <p class="word logo pull-center">
				  	<img src="../resources/images/logo.png"/> 
				  </p>
				  
				</div>
		 <div id="next_prev_btn">
			 	
			 	<a href="http://localhost:2343/Phoenix_Resource_Management_System/Visitor/Home" class=" btn btn-md btn-success pull-left" id="fa_left_btn" >
			 		<span class="fas fa-angle-left  fa-4x text-center"></span>
			 	</a>
			 		
				<a onclick= "submitForm()" class="btn btn-md btn-success pull-right" id="fa_right_btn">
			 		<span class="fas fa-angle-right  fa-4x text-center"></span>
			 	</a>
			 			 
		 	</div>
		
	</header>
	
	<section >
		<form id="registration_form" action ="RegisterVisitor" method="post">
			<div class="form-group">
				<input type="text" class="form-control input-lg" name="visitorFirstName" placeholder="First Name" required/>
			</div>
			<div class="form-group">
				<input type="text" class="form-control input-lg" name="visitorLastName" placeholder="Last Name" required/>
			</div>
			<div class="form-group">
				<input type="email" class="form-control input-lg" name="visitorEmail" placeholder="Email"/>
			</div>
			<div class="form-group">
				<input type="text" class="form-control input-lg" name="visitorPhone" placeholder="Phone" required/>
			</div>
			<div class="form-group">
				<input type="text" class="form-control input-lg" name="visitorVisitingName" placeholder="Host"/>
			</div>
			<div class="form-group">
				<textarea class="form-control" rows="3" name="visitorVisitPurpose" placeholder="Reason for visit" required></textarea>
			</div>
			<br>
			<label>Company:</label>
			<br>
			<div class="btn-group btn-group-toggle data-toggle" >
				<label class="btn btn-primary radio-inline radio_label">
					<input type="radio" name="visitorCompanyName" id="option1" value="Syntel" checked>
					Syntel
				</label>
				<label class="btn btn-primary radio-inline radio_label">
					<input type="radio" name="visitorCompanyName" id="option2" value="AmericanExpress">
					American Express
				</label>
				<label class="btn btn-primary radio-inline radio_label">
					<input type="radio" name="visitorCompanyName" id="option3" value="Other">
					Other
				   <input type="text" class="form-control" id="other" name="otherCompanyName" placeholder="Company">
					
				</label>
			</div>
			
			<input type="submit" id="submit_btn" >
			
		</form>
	</section>
	
	<script>
		$('input[name="visitorCompanyName"]').change(function(){
			$('.radio_label').css('backgroundColor', '#004837');
			$(this).parent().css('backgroundColor', '#FF6347');
		});
	    
		$('#other').hide();
		$('#submit_btn').hide();
		$("#form_btn").hide(); //hide form submit button; the right arrow button uses this button to triger the submission of the form
		$('input[name="visitorCompanyName"]').change(function(){
		    if ($('#option3').is(':checked')) {
		        $('#other').show();
		    } else {
		        $('#other').hide();
		    }
		});
		
		function submitForm(){
			$("#submit_btn").click();
		};
		
		function startTime() {
		    var today = new Date();
		    var h = today.getHours();
		    var m = today.getMinutes();
		    var s = today.getSeconds();
		    m = checkTime(m);
		    s = checkTime(s);
		    document.getElementById('timer').innerHTML =
		    h + ":" + m + ":" + s;
		    var t = setTimeout(startTime, 500);
		}
		function checkTime(i) {
		    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
		    return i;
		}
	</script>
</body>
</html>