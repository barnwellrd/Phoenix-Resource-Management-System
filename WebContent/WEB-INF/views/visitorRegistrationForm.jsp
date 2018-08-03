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
</head>
<body>
	<div class="container">
		<h1> Register to PRMS Services! </h1>
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
				<textarea class="form-control" rows="3" name="visitorPurpose" placeholder="Reason for visit" required></textarea>
			</div>
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
			<br>
			<button type="submit" class="btn btn-lg btn-success pull-right" value="Register">Check In</button>	
		</form>
	</div>
	
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