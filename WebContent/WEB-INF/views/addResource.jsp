<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
    
    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Pop up</title>
  <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <style>
        .modal-dialog{
            border-radius:25px;
            border-color: darkgreen;
            text-align: center;
            width: 370px;
            
        }
        
        .modal-header{
            background-color: green;
            color:white;
            text-align: center;
        }
        
        .modal-body{
             border-radius:25px;            
        }
        
        .resInput input, #resssTypeId, #resLoc {
        	text-align: center;
        	width: 250px;
        	height: 35px;
        }
        
        input[type=number] {
        	text-align: center;
        }
        
        select {
        	text-align-last: center;
        }
        
        input:focus::-webkit-input-placeholder { color:transparent; }
        
        td {
        	padding: 20px;
        }
    
        .checks label:hover{
             background-color:lightgray;
        }
    </style>
        <script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();   
    });
    </script>  
</head>
<body>

<div class="container">
  <h5></h5>
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal" style="padding-left:10px;">Add Resource <br><span class="glyphicon glyphicon-plus-sign" style="font-size:20px; text-align:center;"></span> <br> </button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog" style=" border-radius:25px;">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Resource Manager</h4>
        </div>
 
        <div class="modal-body">
            
        <form action="insertResource" method="post">  
        	<div class="resInput">
        		
        		<select id="resLoc" name="resLoc" required>
	                <option value="" disabled selected>Select a Location</option>
		              	<c:forEach items="${locPopRes}" var="locIdOptions">
		                <c:set var="locTypeId" value="${fn:substring(locIdOptions, 0, 6)}" />
		                <option value="${locTypeId}">${locIdOptions}</option>
	                </c:forEach>
                </select>
        		<br><br>
        		
	        	<select id="resssTypeId" name="resssTypeId" required>
	                <option value="" disabled selected>Select a Resource</option>
	              	<c:forEach items="${listPopRes}" var="res1">
	                	<c:set var="resTypeId" value="${fn:substring(res1, 0, 4)}" />
	                	<c:set var="resTypeName" value="${fn:substringAfter(res1, resTypeId)}" />
	                	<option value="${resTypeId}" name="test">${resTypeName}</option>
	                </c:forEach>
                </select>
	             <br><br>
				<input type="text" id="roomNum" name="roomNum" placeholder="Room Number" required></input>
				<br><br>
				<input type="number" min="1" id="capacity" name="capacity" placeholder="Capacity" required/>
            
            </div>
            <br>
            <label>Features:</label><br>
             <div class="checks">
    			<table align="center">
	    			<tr>
	    			 <td><label for="numResProj" data-toggle="tooltip" title="Projector"><span class="glyphicon glyphicon-film" style="font-size:40px;"><span></span></span></label>
	                	<br>
	                	<input id="numResProj" value="0" type="number" min ="0" name="numResProjName" style="width:55px; height:35px;" required />
	                	</td>
	                <td><label for="numResPrint"data-toggle="tooltip" title="Printer"><span class="glyphicon glyphicon-print"style="font-size:40px"><span></span></span></label>
	                <br>
	                <input id="numResPrint" value="0" type="number" min ="0" name="numResPrintName" style="width:55px; height:35px;" required />
	                </td>
	                <td><label for="numResVid"data-toggle="tooltip" title="Video Conference Camera"><span class="glyphicon glyphicon-facetime-video"style="font-size:40px"><span></span></span></label>
	                <br>
	                <input id="numResVid" value="0" type="number" min ="0" name="numResVidName" style="width:55px; height:35px;" required />
	                </td>
	                </tr>
					<tr>
					<td><label for="numResTV"data-toggle="tooltip" title="TV"><span class="glyphicon glyphicon-blackboard"style="font-size:40px"><span></span></span></label> 
	                <br>
	                <input id="numResTV" value="0" type="number" min ="0" name="numResTVName" style="width:55px; height:35px;" required />
	                </td>
	                <td><label for="numResWhiteBoard"data-toggle="tooltip" title="White Board"><span class="glyphicon glyphicon-pencil"style="font-size:40px"><span></span></span></label> 
	                <br>
	                <input id="numResWhiteBoard" value="0" type="number" min ="0" name="numResWhiteBoardName" style="width:55px; height:35px;" required/>
	                </td>
	                <td><label for="numResFood"data-toggle="tooltip" title="Food/Utensils"><span class="glyphicon glyphicon-cutlery"style="font-size:40px"><span></span></span></label>    
					<br>
					<input id="numResFood" value="0" type="number" min ="0" name="numResFoodName" style="width:55px; height:35px;" required/>
	                </td>
	                </tr>
				</table>
			</div>	
				 <table align="center">
	            <tr><td>
	            <label>Super Room: </label>
	            	<input type="hidden" value="0" name="isSuperRoom"/>
				  	<input type="checkbox" value="1" name="isSuperRoom"/>
				  <span class="checkmark"></span>		
	            </td></tr>
	            </table>
				
				<label>Description:</label> <br>
				<textarea id="desc" name="desc" style="width:250px; height:80px;" required></textarea>
                <div class="modal-footer">
            		<input type="submit" value="Submit"/>
        		</div>        
            </form>     
       </div> 
       </div>
    </div>
  </div>
</div>
</body>
</html>