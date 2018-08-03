<%@page import="rms.dao.UsersJdbcTemplate"%>
<%@page import="rms.model.Users"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Check in</title>
	<!-- Latest compiled and minified CSS -->
	

	<link rel="stylesheet" href="resources/css/checkin.css">
	
	<script src="resources/js/anime.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<script src="resources/js/jquery.js"></script>

	<link rel="stylesheet" href="resources/css/fontawsome/all.css">
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	
	
	<script type="text/javascript">
			
		$(document).ready(function(){	
		
		    anime.timeline({loop: false})
		    .add({
		      targets: '.ml15 .word',
		      scale: [24,1],
		      opacity: [0,1],
		      easing: "easeOutCirc",
		      duration: 100,
		      delay: function(el, i) {
		        return 150 * i;
		      }
		    })
		    
		});
			
	</script>
	
	<style>
		body{
			
			background-image: url("resources/images/checkin_index.png");
			background-size: 100% auto;
			background-repeat: no-repeat;
			background-position: center botton;
		}
		.section_content{
			margin: auto;
			margin-bottom: 5%;
			
		}
		
		.ml15{
		}
		section{
			padding-top: 10%;
		}
		.section_block{
			max-width: 50%;
			min-height: 100%;
			
		}
		header{
			
			 background-color: white;
		}
		.welcome, .to, .logo{
			margin-right: 2%;
		}
		
		.btn_string{
			padding-left: 5%;
			font-size: 50%;
		}

		#checkout_btn{
			background-color: #004837;
			color:#e85200;
		}
	    #checkin_btn{
	    	background-color: #e85200;
			color:#004837;
		}
		
		.main_btn{
			min-height: 100px;
		}
		

	</style>
	
</head>

<body class="container-fluid text-center">
   
	<header  class="container" id="title" style="background-color: white" >
				<h2 class="ml15">
				  <span class="word">W </span> 
				  <span class="word">e </span> 
				  <span class="word">l </span>
				  <span class="word">c </span>
				  <span class="word">o </span>
				  <span class="word">m </span>
				  <span class="word welcome">e </span>
				  <span class="word to">To </span>
				  <span class="word logo">
				  	<img src="resources/images/logo.png"/> 
				  </span>
				  <span class="word ">P </span> 
				  <span class="word ">h </span>
				  <span class="word ">o </span>
				  <span class="word state">e </span>
				  <span class="word ">n </span>
				  <span class="word ">i </span>
				  <span class="word ">x</span>
				</h2>
	</header>

	<section class="container"> 

		<!--This div will hold a registration button; when clicked it will open the registration form-->
		
		<div class="container section_block">
		
			<form action="/Phoenix_Resource_Management_System/registration" class="card section_content text-center" id="check_in_block">
				<button  class="btn btn-lg btn-success btn-block text-center main_btn"  id="checkin_btn">
					<span class="fas fa-sign-in-alt fa-2x"><h5 class="btn_string">Check in</h5></span>
					
				</button>
				
			</form>
			
			<form action="/Phoenix_Resource_Management_System/checkOutForm" class="card section_content text-center" >
				
				<button  class="btn btn-lg btn-danger btn-block text-center main_btn" id="checkout_btn">
					<span class="fas fa-sign-out-alt fa-2x text-center"><h5 class="btn_string">Check out</h5></span>			
					
			    </button>
					
			</form>
				
		</div>
	</section>

</body>
</html>