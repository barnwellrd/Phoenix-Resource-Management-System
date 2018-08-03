<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">  
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">  
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>  
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
	<title>Check Out</title>
</head>
<body>
	<style>
		body {
			background-size:contain;
	     background-image: url("resources/images/bg_phx.png");
	     background-repeat: no-repeat;
	     background-position: bottom center;
 } 
		
#checkout_btn{
            background-color: #e85200;
            color:#004837;
        }
.btn_string{
            padding-left: 5%;
            font-size: 30%;
        }
		
	</style>
	<div class="container container center-block" style="padding:150px;">
  <form action="checkOut" method="post" style="background-color:white; padding:50px; border-style:solid;border-color: grey;border-radius: 5px;">
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