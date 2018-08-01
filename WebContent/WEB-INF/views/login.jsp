<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
		
		<spring:url value="/resources/css/login.css" var="loginCSS" />
		<spring:url value="/resources/css/bootstrap.min.css" var="bootMin" />
		<spring:url value="/resources/js/jquery.js" var="jquery" />
		<spring:url value="/resources/js/bootstrap.js" var="bootJS" />
		
		<spring:url value="/resources/images" var="images" />
		
		<link rel="stylesheet" href="${bootMin}" />
		<link rel="stylesheet" href="${loginCSS}" />
		
		<script src="${jquery}"></script>
		<script src="${bootJS}"></script>
		    
	</head>
<body>
<div class="login-page">
  <div class="form" style="opacity=2.5">
  
  	<img src="${images}/book-logo.png" alt="logo" style="height:100%; width:100%; padding-bottom:60px;" />
      
    <form class="register-form">
      <input type="text" placeholder="name"/>
      <input type="password" placeholder="password"/>
      <input type="text" placeholder="email address"/>
      <button>create</button>
      <p class="message">Already registered? <a href="#">Sign In</a></p>
    </form>

      
      <form action="booking"> 
		  <div class="input-group">
		    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		    <input id="email" type="text" class="form-control" name="email" placeholder="Email" required>
		  </div>
		          <br>
		  <div class="input-group">
		    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		    <input id="password" type="password" class="form-control" name="password" placeholder="Password" required>
		  </div>
		  <button type="submit"> <span class="glyphicon glyphicon-log-in">  login</span></button>
		 
	</form>
      
      <br>
            
      <p class="message">Not registered? <a href="#">Create an account</a></p>
      <br>
  		<img src="${images}/syntrans.png" alt="logo" style="height:50%; width:50%; padding-bottom:60px;"  />
             
    </form>
  </div>
</div>
</body>
</html>
