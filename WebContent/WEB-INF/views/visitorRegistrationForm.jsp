<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html>
<html>
<head>
	<title>Register Now!</title>
	<link rel="stylesheet" href="../resources/fontawesome/css/all.css">
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="../resources/css/visitorViews.css">
	<script src="../resources/js/jquery.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/jquery.js"></script>
</head>

<body class="container-fluid text-center">
	<header class="container text-center" id="title">
		<div class="ml15 pull-center">
			<h1>
				<span class="word">W </span> <span class="word">e </span> <span
					class="word">l </span> <span class="word">c </span> <span
					class="word">o </span> <span class="word">m </span> <span
					class="word welcome">e </span> <span class="word to">To </span> <span
					class="word ">P </span> <span class="word ">h </span> <span
					class="word ">o </span> <span class="word state">e </span> <span
					class="word ">n </span> <span class="word ">i </span> <span
					class="word ">x</span>
			</h1>
			<p class="word logo pull-center">
				<img src="../resources/images/logo.png" />
			</p>
		</div>
	</header>
	<section class="nav">
	<div id="next_prev_btn">

		<a href="/Phoenix_Resource_Management_System/Visitor/Home"
			class=" btn btn-md btn-success pull-left" id="fa_left_btn"> <span
			class="fas fa-angle-left  fa-4x text-center"></span>
		</a> <a onclick="submitForm()" class="btn btn-md btn-success pull-right"
			id="fa_right_btn"> <span
			class="fas fa-angle-right  fa-4x text-center"></span>
		</a>
	</div>
	</section>
	<section class="container">
	<form id="registration_form" action="RegisterVisitor" method="post">
		<div class="form-group">
			<input type="text" class="form-control" name="visitorFirstName"
				placeholder="First Name" required />
		</div>
		<div class="form-group">
			<input type="text" class="form-control" name="visitorLastName"
				placeholder="Last Name" required />
		</div>
		<div class="form-group">
			<input type="email" class="form-control" name="visitorEmail"
				placeholder="Email" />
		</div>
		<div class="form-group">
			<input type="text" class="form-control" name="visitorPhone"
				placeholder="Phone" required />
		</div>
		<div class="form-group">
			<input type="text" class="form-control" name="visitorVisitingName"
				placeholder="Host" />
		</div>
		<div class="formgroup">
			<textarea class="form-control" rows="3" name="visitorVisitPurpose"
				placeholder="Reason for visit" required></textarea>
		</div>
		<br> <label>Company:</label> <br>
		<div class="form-group form-inline">
			<label class="radio-inline"> <input type="radio"
				name="visitorCompanyName" id="option1" value="Syntel" checked>
				Syntel
			</label> <label class="radio-inline"> <input type="radio"
				name="visitorCompanyName" id="option2" value="AmericanExpress">
				American Express
			</label> <label class="radio-inline"> <input type="radio"
				name="visitorCompanyName" id="option3" value="Other"> Other
			</label> <input type="text" class="form-control" id="other"
				name="otherCompanyName" placeholder="Company">
		</div>
		<input visible=false id="form_btn" type="submit" />
	</form>
	</section>
	<script>
		$('#other').hide();
		$("#form_btn").hide(); //hide form submit button; the right arrow button uses this button to trigger the submission of the form
		$('input[name="visitorCompanyName"]').change(function() {
			if ($('#option3').is(':checked')) {
				$('#other').show();
			} else {
				$('#other').hide();
			}
		});
		function submitForm() {
			$("#form_btn").click();
		}
	</script>
</body>
</html>