package dbController;

/*
This class constructor created with help from Dr. McCann's JDBC.java example. 

	Class: 
		DBConnect
	Name:
		David Santana
	Dependency:
		oracle.jdbc.OracleDriver
	Purpose:	
		This class created to make querying simpler when a user wants to receive a table as a list of objects.
		Also prevents user from having to import any SQL into their program if using this object and the 
		querying method provided. 
	Class Variables:
		Connection dbconn - the connection to the oracle db
		Statement stmt    - the statement used for all queries
	Constructor:
		DBConnect(username, password)  
			create the Connection dbconn using the provided username and password
	Methods:
		ArrayList<ArrayList<Object>> getQueryAsLists(String query)  
			execute the query argument and return a 2d arraylist containing the resulting
			relation's columns and rows. 
							
		void close()
			close the statement and connection to the DB. 
*/
import java.util.ArrayList;
import java.sql.*;          

public class DBConnect{
      
	Connection dbconn;//the connection to the database. 
	Statement stmt; //the statement used to execute all queries

	/*
		CONSTRUCTOR DBConnect(username, password)

		Creates a connection to the oracle database using the username and password
		arguments. 

	*/
	public DBConnect(String username, String password){


	    final String oracleURL = "jdbc:oracle:thin:@localhost:1521:XE";
	
		 try {

                Class.forName("oracle.jdbc.OracleDriver");

        } catch (ClassNotFoundException e) {

                System.err.println("ClassNotFoundException: Error loading Oracle JDBC driver.");
                System.exit(-1);

        }

        // make and return a database connection to the user's
        // Oracle database
        dbconn = null;

        try {
                dbconn = DriverManager.getConnection(oracleURL,username,password);

        } catch (SQLException e) {

                System.err.println("SQLException: Could not open JDBC connection.");
                System.err.println("Message:   " + e.getMessage());
                System.err.println("SQLState:  " + e.getSQLState());
                System.err.println("ErrorCode: " + e.getErrorCode());
                System.exit(-1);

        }
	}

	/*
		Creates a string that puts the column data into an html table. 
	*/
	public static String toTable (ArrayList<ArrayList<Object>> table){

		String finalStr="";
			
		finalStr+="<table id='dbConnect' style='border:1px solid black'>";	

		//table headers row
		finalStr+="<tr>";
		
		//table headers added seperately so the th tag can be used instead of td
		for(int i=0; i<table.size(); i++){

			finalStr+="<th style='border:1px solid black'>";
			finalStr+=table.get(i).get(0);
			finalStr+="</th>";
		}

		//close table header row. 
		finalStr+="</tr>";

		//start i at 1 bc table headers have been preadded. 
		for(int i=1; i<table.get(0).size(); i++){
			finalStr+="<tr>";
			//for each column in the relation
			for(int j=0; j<table.size();j++){
				finalStr+="<td style='border:1px solid black'>";
				finalStr+=table.get(j).get(i);
				finalStr+="</td>";
			}
			finalStr+="</tr>";
		}

		finalStr+="</table>";

		return finalStr;
	}

	/*	toString (ArrayList<ArrayList<Object>>)
		Accepts an ArrayList of arraylists of type object, returns a string which shows columns 
		in a similar form to what plsql will print when displaying a relation. 
	*/
	public static String toString (ArrayList<ArrayList<Object>> table){

		String finalStr="";

		//the length of the longest string in each column. 
		//added to this list in order that they exist in the table object. 
		ArrayList<Integer> sizes = new ArrayList<Integer>();

		//find the greatest length string in each column and add that length to sizes arraylist. 
		for(int i=0; i<table.size();i++){

			ArrayList<Object> currList = table.get(i);

			int greatest=0;

			//each item in currList. 
			for (int j=0; j<currList.size();j++){

				String objstr;

				//some fields are null which will throw nullPointerException if tried to toString()
				if(currList.get(j)!=null)
					objstr = currList.get(j).toString();
				else
					objstr = "null";

				if(greatest<objstr.length())
					greatest = objstr.length();
			}

			sizes.add(greatest);	

		}

		//for num rows in the table. 	
		for(int i=0; i<table.get(0).size();i++){

			finalStr+="\n";
			//column seperator. 
			finalStr+=" | ";

			//for all columns
			for (int j=0; j<table.size();j++){

				String objstr;

				//some fields are null which will throw nullPointerException if tried to toString()
				if(table.get(j).get(i)!=null)
					objstr = table.get(j).get(i).toString();
				else
					objstr = "null";

				finalStr+=objstr;
	
				//print objstr.length()-sizes.get(j)+2 whitespaces this is to make
				//the columns print in clean columns. 
				for(int p=objstr.length(); p<sizes.get(j);p++){
					finalStr+=" ";
				}
				
				finalStr+=" | ";
			
			}
		}

		finalStr+="\n";


		return finalStr;

	}

	/*
		close()
		close this db connection

	*/
	public void close(){

		try{
		
			//if a query is never made stmt could be null	
			if(stmt!=null)
				stmt.close();
			dbconn.close();	
		}
		catch(SQLException e){
			System.out.println("Error trying to close db connection");
			System.exit(-1);
		}

	}

	public ArrayList<ArrayList<Object>> getQueryAsLists (String query){

		ResultSet answer = null;
		ArrayList<ArrayList<Object>> table = null;

        try {

			//make the resultset scrollable and read only 
            stmt = dbconn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			//execute query provided by user in the argument
	        answer = stmt.executeQuery(query);

			ResultSetMetaData metadata = answer.getMetaData();

			//number of columns in the result. 
			int numColumns = metadata.getColumnCount();

			//create arraylist of arraylist with initial capacity of numColumns
			table = new ArrayList<ArrayList<Object>>(numColumns);

			//for all columns in the result set, add a new arraylist with the values in that column. 	
			for (int i=1; i<=numColumns;i++){

				// add a new list for each column 
				table.add(new ArrayList<Object>());
				table.get(i-1).add(metadata.getColumnName(i));

				while(answer.next()){

					//table.get(i-1) will contain column i from the resultSet,
					//because sql column starts at 1, arraylist starts at 0.
					table.get(i-1).add(answer.getObject(i));
				}
			
				//set the cursor to the top of the rows. 	
				answer.beforeFirst();
			}

        } catch (SQLException e) {

                System.err.println("*** SQLException: Could not fetch query results.");
                System.err.println("Message:   " + e.getMessage());
                System.err.println("SQLState:  " + e.getSQLState());
                System.err.println("ErrorCode: " + e.getErrorCode());
                System.exit(-1);

        }

		return table;
	}

	public void execute(String query){

		try{

        stmt = dbconn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		stmt.executeQuery(query);


        } catch (SQLException e) {

                System.err.println("*** SQLException: Could not fetch query results.");
                System.err.println("Message:   " + e.getMessage());
                System.err.println("SQLState:  " + e.getSQLState());
                System.err.println("ErrorCode: " + e.getErrorCode());
                System.exit(-1);

        }



	}
}
