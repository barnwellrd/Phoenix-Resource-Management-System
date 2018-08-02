

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*, org.springframework.web.context.WebApplicationContext,
org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<html lang="en">
<head>
  <title>Syntel Resource Booking</title>

  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    	
	<spring:url value="/resources/css/bootstrap.min.css" var="bootMin"/>
	<spring:url value="/resources/css/dashboard.css" var="DashboardCSS"/>
	
	<link rel="stylesheet" href="${bootMin}"/>
	<link rel="stylesheet" href="${DashboardCSS}"/>
	
	
	<spring:url value="/Js" var="JS"/>
	
	<script src="${JS}/jquery.js"></script>
	<script src="${JS}/bootstrap.js"></script>
	

  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
   
      
      
      
      .container {
   padding:0px;
    
       
}
      
 
      .container, #footer{
          background-color:white;
     
        
      }
      .navbar {
      margin-bottom: 0;
      border-radius: 0;
     background-color:white;
       
    }
    
      
  
      
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {
        height: 550px;
        
      
      }
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color:rgb(0,51,0);
      height: 550px;
    }
      
      
      div.row.content{
          background-image: url("resources/images/green.jpg");
          
      }
    
    /* Set black background color, white text and some padding */
    footer {
     color:rgb(0,51,0);
       background-color: white;
      height:60px;
    
      padding: 0px;
        position: absolute;
        width: inherit;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {
          height:auto;
        } 
    }
      
      
          @media screen and (max-width: 767px) {
      .card {
        max-height: 100px;
        max-width:100px;
        padding: 15px;
      }
      }
      
      
      #bod{
          
          background-color:#eee9e9	;
        /*background-image: url("green.jpg"); /* fallback for old browsers */

      }
      
  </style>
</head>
<body id="bod">
    <div class="container">
    
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
          <img src="resources/images/syntrans.png" alt="logo" style="height:30%; width:20%; padding-top:0px; padding-bottom:10px; "> 
    
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
       
    
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
     <ul class="nav navbar-nav navbar-center" style="left-padding:300px;" >
              
         
         
        </ul>
     
        <ul class="nav navbar-nav navbar-right">
              
        <li style="font-size:22px;"><a href="#"><span class="glyphicon glyphicon-user"></span> </a></li>
               
   <li style="font-size:22px;"><a href="#"><span class="glyphicon glyphicon-bell"></span> </a></li>
                   
      <li style="font-size:22px;"><a href="#"><span class="glyphicon glyphicon-search"></span> </a></li>
          
            
      </ul>
    </div>
  </div>
    </div>
</nav>
    
    
    
    
    
    
  
<div class="container text-center" >    
  <div class="row content">
    <div class="col-sm-1 sidenav">
        <span id="linkside">
       
        
            <div class="panel-group">

       
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" id="burger" style="font-color:white;" data-toggle="dropdown"><span class="glyphicon glyphicon-menu-hamburger" style="font-size:50px; text-align:center; color:white;"></span></a>
          <ul class="dropdown-menu">
              <br>
            <li><a href="#">Book <span class="glyphicon glyphicon-book pull-right"></span></a></li>
            <li class="divider"></li>
             
            <li><a href="#">Upcoming <span class="glyphicon glyphicon-folder-open pull-right"></span></a></li>
            <li class="divider"></li>
           
               <li><a href="#">Previous <span class="glyphicon glyphicon-retweet pull-right"></span></a></li>
            <li class="divider"></li>
              
              
            <li class="divider"></li>
            <li><a href="#">Calendar <span class="glyphicon glyphicon-calendar pull-right"></span></a></li>
            <li class="divider"></li>
            <li><a href="#"><span class="glyphicon glyphicon-remove pull-right"></span>Cancel </a></li>
          </ul>
        </li>
        
   
            </div>
            
            
            
            
            
        </span>
    </div>
      
      
    <div class="col-sm-8 text-left"> 
    <br>
<!--
 <div class="well well-lg" style="background-color:rgb(0,50,0); color:white;"><h1>   Resource Booker </h1> </div> -->
        <div class="help" style="background-color:rgb(0,50,0); border-radius:25px;">
          <center>  
            <img src="resources/images/rms.png" alt="rmsLogo" style=width:300px;">
            
            </center>
 </div>
        <br>
        <br>

            
       
            
   <div class="row">
       
  <div class="column">
      
    <div class="card" id="hi" style="width:400px; height:300px;">
      <p><i class="fa fa-user"></i></p>
      <h1>Book</h1>
      <p>Book A Room</p>
      <p>  <span class="glyphicon glyphicon-book" style="font-size:150px;"></span></p>
    </div>
  </div>
<center>
  <div class="column">
<br>
  </div> 

  <div class="column">
      
    <div class="card" id="bye" style="width:400px; height:300px;">
                            
      <p><i class="fa fa-coffee"></i></p>
                           
                                
      <h1>Resources</h1>
      <p>View Resources</p>
      <p>  <span class="glyphicon glyphicon-th" style="font-size:150px;"></span></p>
    </div>
  </div>
      
</div>
        
        
     
        
        
 
        
    </div>
           
     
  
      </center> 
   
           
</div>


<footer class="footer" id="footer"">
  <p>© 2018 Syntel, Inc </p>
                                          
</footer>   

</body>
</html>
