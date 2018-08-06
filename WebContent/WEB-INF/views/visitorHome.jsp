<%@page import="rms.dao.UsersJdbcTemplate"%>
<%@page import="rms.model.Users"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Visitor Home</title>
	<link rel="stylesheet" href="../resources/fontawesome/css/all.css">
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="../resources/css/visitorViews.css">
	<script src="../resources/js/anime.min.js"></script>
	<script src="../resources/js/bootstrap.js"></script>
	<script src="../resources/js/jquery.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			anime.timeline({
				loop : false
			}).add({
				targets : '.ml15 .word',
				scale : [ 24, 1 ],
				opacity : [ 0, 1 ],
				easing : "easeOutCirc",
				duration : 100,
				delay : function(el, i) {
					return 25 * i;
				}
			})
		});
	</script>
</head>

<body id="home-page-body"class="container-fluid text-center">
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
	<section class="nav"></section>
	<section class="container">
		<div class="container section_block">
			<form
				action="/Phoenix_Resource_Management_System/Visitor/Registration"
				class="card section_content text-center home-form"
				id="check_in_block">
				<button
					class="btn btn-lg btn-success btn-block text-center main_btn"
					id="checkin_btn">
					<span class="fas fa-sign-in-alt fa-3x"><h5
							class="btn_string">
							<b>Check In</b>
						</h5></span>
				</button>
			</form>
			<form action="/Phoenix_Resource_Management_System/Visitor/COForm"
				class="card section_content text-center home-form">

				<button class="btn btn-lg btn-danger btn-block text-center main_btn"
					id="checkout_btn">
					<span class="fas fa-sign-out-alt fa-3x text-center"><h5
							class="btn_string">
							<b>Check Out</b>
						</h5></span>
				</button>
			</form>
		</div>
	</section>
</body>
</html>