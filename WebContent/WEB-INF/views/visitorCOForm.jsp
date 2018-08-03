<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">  
	<!-- <link rel="stylesheet" href="../resources/fontawesome/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	-->
	<link rel="stylesheet" href="../resources/fontawesome/css/all.css">
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css"> 
	<link rel="stylesheet" href="../resources/css/visitorCheckOutForm.css">   
	<script src="../resources/js/jquery.min.js"></script>  
	<script src="../resources/js/bootstrap.min.js"></script>
	
	<title>Check Out</title>
</head>
<body>
	<div class="container container center-block" style="padding:150px;">
  <form action="CheckOut" method="post" style="background-color:white; padding:50px; border-style:solid;border-color: grey;border-radius: 5px;">
    <div class="form-group">
      <input type="text" class="form-control text-center" name="name" value="Enter Name" style="border-radius: 5px;">
    </div>

    <h3 class="text-center">OR</h3>
    <div class="form-group">
      <input type="text" class="form-control text-center" name="bid" value="Enter Badge ID" style="border-radius: 5px;">
    </div>
    <br>
    <div class="form-group">      
       <button  class="btn btn-lg btn-danger btn-block text-center main_btn" id="checkout_btn">
       		<span class="fas fa-sign-out-alt fa-5x text-center"><h5 class="btn_string">Check out</h5>
       		</span>
       	</button>          
    </div>
  </form>
</div>
</body>
</html>