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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	
	<link rel="stylesheet" href="resources/css/checkin.css">
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.0.2/anime.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	
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
			background-color: white/*white*/;
			background-image: url("https://media.discordapp.net/attachments/471733770198974484/474642353186013234/Login_BG.png");
			background-size: 100% auto;
			background-repeat: no-repeat;
			background-position: center;
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
			/*border-style: solid;
			border-width: 2px;
			border-top-width: 0;
			border-color: #004837;
			margin-top: 5%;**/
		}
		.welcome, .to, .logo{
			margin-right: 2%;
		}
		
		.btn_string{
			padding-left: 5%;
			font-size: 30%;
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
		
		@media screen and (min-width: 480px){
			body{
			background-color: white/*white*/;
			background-image: url("https://media.discordapp.net/attachments/471733770198974484/474642353186013234/Login_BG.png");
			background-size: 100% auto;
			background-repeat: no-repeat;
			background-position: center;
			}
		
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
				  	<img src="https://cdn.discordapp.com/attachments/471595089777655808/472189472994492416/logo.png"/> 
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
		
			<div class="card section_content text-center" id="check_in_block">
				<button  class="btn btn-lg btn-success btn-block text-center main_btn"  id="checkin_btn">
					<span class="fas fa-sign-in-alt fa-5x"><h5 class="btn_string">Check in</h5></span>
					
				</button>
				
			</div>
			
			<div class="card section_content text-center" >
				
				<button  class="btn btn-lg btn-danger btn-block text-center main_btn" id="checkout_btn">
					<span class="fas fa-sign-out-alt fa-5x text-center"><h5 class="btn_string">Check out</h5></span>			
					
			    </button>
					
			</div>
				
		</div>
	</section>

	<footer>
	</footer>
</body>
</html>