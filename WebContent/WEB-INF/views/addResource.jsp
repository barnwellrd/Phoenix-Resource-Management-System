<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
        	<label>Custom Name: </label>
            <input type="text" id="custName" name="custName" placeholder="Custom Name">
            <br>
            <br>
            <label>Type of Room:</label>
            <select id="roomType" name="roomType" placeholder="testing">
              <option value="" disabled selected>Select a Resource</option>
              <option name="Board" value="1001">Board</option>
              <option name="Break" value="1001">Break</option>
              <option name="Conference" value="1002">Conference</option>
              <option name="Creative" value="1001">Creative</option> 
              <option name="SCRUM" value="1001">SCRUM</option>
            </select>
			<br><br>
			<label>Room Number:</label>
			<input type="text" id="roomNum" name="roomNum"></input>
			<br><br>
            <label>Capacity:</label>
            <input type="number" min="1" id="capacity" name="capacity" placeholder="Capacity">
            <br>
            <br>
            <label>Features:</label><br>
            <div class="checks">
    			<table align="center">
	    			<tr>
	    			 <td><label for="check1" data-toggle="tooltip" title="Projector"><span class="glyphicon glyphicon-film" style="font-size:40px;"><span></span></span></label>
	                	<br>
	                	<input id="check1" type="number"placeholder="0" min ="0" name="check1" value="check1" style="width:35px; height:25px;" />
	                	</td>
	                <td><label for="check2"data-toggle="tooltip" title="Printer"><span class="glyphicon glyphicon-print"style="font-size:40px"><span></span></span></label>
	                <br>
	                <input id="check2" type="number"placeholder="0" min ="0" name="check2" value="check2" style="width:35px; height:25px;" />
	                </td>
	                <td><label for="check3"data-toggle="tooltip" title="Video Conference Camera"><span class="glyphicon glyphicon-facetime-video"style="font-size:40px"><span></span></span></label>
	                <br>
	                <input id="check3" type="number"placeholder="0" min ="0" name="check3" value="check3" style="width:35px; height:25px;" />
	                </td>
	                </tr>
					<tr>
					<td><label for="check4"data-toggle="tooltip" title="TV"><span class="glyphicon glyphicon-blackboard"style="font-size:40px"><span></span></span></label> 
	                <br>
	                <input id="check4" type="number"placeholder="0" min ="0" name="check4" value="check4" style="width:35px; height:25px;" />
	                </td>
	                <td><label for="check5"data-toggle="tooltip" title="White Board"><span class="glyphicon glyphicon-pencil"style="font-size:40px"><span></span></span></label> 
	                <br>
	                <input id="check5" type="number"placeholder="0" min ="0" name="check5" value="check5" style="width:35px; height:25px;"/>
	                </td>
	                <td><label for="check6"data-toggle="tooltip" title="Food/Utensils"><span class="glyphicon glyphicon-cutlery"style="font-size:40px"><span></span></span></label>    
					<br>
					<input id="check6" type="number"placeholder="0" min ="0" name="check6" value="check6" style="width:35px; height:25px;"/>
	                </td>
	                </tr>
					</div>
				</table>
				<label>Description:</label> <br>
				<textarea id="desc" name="desc" style="width:250px; height:80px;"></textarea>
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