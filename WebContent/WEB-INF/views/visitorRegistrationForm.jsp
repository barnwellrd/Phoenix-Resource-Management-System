<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Register Now!</title>

	<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
	<script src="../resources/js/jquery.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="../resources/css/visitorRegistrationForm.css">
    <link rel="stylesheet" href="../resources/fontawesome/css/all.css">
	
</head>


<body class="container" >
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
				  </h1>
				  <p class="word logo pull-center">
				  	<img src="../resources/images/logo.png"/> 
				  </p>
				</div>
		 <div id="next_prev_btn">
			 	
			 	<a href="http://localhost:2343/Phoenix_Resource_Management_System/Visitor/Home" class=" btn btn-md btn-success pull-left" id="fa_left_btn" >
			 		<span class="fas fa-angle-left  fa-4x text-center"></span>
			 	</a>
			 		
				<form action="RegisterVisitor" method="post">
					<a type="submit" value="register" class=" btn btn-md btn-success pull-right" id="fa_right_btn">
			 			<span class="fas fa-angle-right  fa-4x text-center"></span>
			 		</a>
			 	</form>
			 			 
		 	</div>
		 
	</header>
	
	<section >
		<form action ="RegisterVisitor" method="post">
			<div class="form-group">
				<input type="text" class="form-control" name="visitorFirstName" placeholder="First Name" required/>
			</div>
			<div class="form-group">
				<input type="text" class="form-control" name="visitorLastName" placeholder="Last Name" required/>
			</div>
			<div class="form-group">
				<input type="email" class="form-control" name="visitorEmail" placeholder="Email"/>
			</div>
			<div class="form-group">
				<input type="text" class="form-control" name="visitorPhone" placeholder="Phone" required/>
			</div>
			<div class="form-group">
				<input type="text" class="form-control" name="visitorVisitingName" placeholder="Host"/>
			</div>
			<div class="formgroup">
				<textarea class="form-control" rows="3" name="visitorVisitPurpose" placeholder="Reason for visit" required></textarea>
			</div>
			<br>
			<label>Company:</label>
			<br>
			<div class="form-group form-inline">
				<label class="radio-inline">
					<input type="radio" name="visitorCompanyName" id="option1" value="Syntel" checked>
					Syntel
				</label>
				<label class="radio-inline">
					<input type="radio" name="visitorCompanyName" id="option2" value="AmericanExpress">
					American Express
				</label>
				<label class="radio-inline">
					<input type="radio" name="visitorCompanyName" id="option3" value="Other">
					Other
				</label>
				<input type="text" class="form-control" id="other" name="otherCompanyName" placeholder="Company">
			</div>
		
		</form>
	</section>
	
	<script>
		$('#other').hide();
		$('input[name="visitorCompanyName"]').change(function(){
		    if ($('#option3').is(':checked')) {
		        $('#other').show();
		    } else {
		        $('#other').hide();
		    }
		});
	</script>
</body>
</html>