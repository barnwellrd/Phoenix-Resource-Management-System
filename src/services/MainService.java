package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rms.dao.BookingsJdbcTemplate;
import rms.dao.ResourcesJdbcTemplate;
import rms.model.Bookings;

@Controller
public class MainService {

		@RequestMapping(value="/")
		public String homeScreen() {
			return "login";
		}
		
		@RequestMapping(value="/login")
		public String loginScreen() {
			return "login";
		}

		@RequestMapping(value="/booking")
		public String booking() {
			return "booking";
		}
		
		@RequestMapping(value="/deleteEvent")
		public void deleteEvent(HttpServletRequest request, HttpServletResponse response){
			
				int id = Integer.parseInt(request.getParameter("bookingId"));
				new BookingsJdbcTemplate().delete(id);
				
		}
		
		@RequestMapping(value="/getAllBookingsAsTable")
		public void getAllBookingsAsTable(HttpServletRequest request, HttpServletResponse response) {
			
			List<Bookings> allBookings = new BookingsJdbcTemplate().getAll();
			PrintWriter out = null;
			
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			out.print("<table id='dataTable' border='solid' style='display:none'> ");
			
			for(Bookings b: allBookings){
				
				out.print("<tr>");
				String resourceName = new ResourcesJdbcTemplate().search(b.getResourceId()).getResourceName();

				out.print("<td>"+resourceName+"</td>");
				out.print("<td>"+b.getBookedStartTime()+"</td>");
				out.print("<td>"+b.getBookedEndTime()+"</td>");		
				out.print("<td>"+b.getBookingId()+"</td>");
				out.print("</tr>");
			}
			
			out.print("</table>");
			
		}
				
		@RequestMapping(value="/addEvent")
		public void addEvent(HttpServletRequest request, HttpServletResponse response) {
			
		    String date = request.getParameter("date");
			String[] dates = date.split("-");
			
			String timeTo = request.getParameter("timeTo");
			String timeFrom = request.getParameter("timeFrom");
			
		   	DateTimeFormatter format = DateTimeFormatter.ofPattern("uu/MM/dd HH:mm");
		  
			String dates1 = dates[0]+""+timeFrom;
			String dates2 = dates[1]+" "+timeTo;
			dates2=dates2.trim();

			//translate the calendar date into a date for the database. 
		   	LocalDateTime date1 = LocalDateTime.parse(dates1,format);
		   	LocalDateTime date2 = LocalDateTime.parse(dates2,format);
		   	Timestamp setter1 = new Timestamp(date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		   	Timestamp setter2 = new Timestamp(date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

		   	Bookings newB = new Bookings();
			newB.setIsActive(1);
			newB.setBookedStartTime(setter1);
			newB.setBookedEndTime(setter2);
			newB.setResourceId(1001);
			newB.setUserId(101);
		   	
		    new BookingsJdbcTemplate().insert(newB);
		    List<Bookings> all = new BookingsJdbcTemplate().getAll();
		    int id = 0 ;
		    for(Bookings b : all){
		    	if(b.getBookingId()>id)
		    		id=b.getBookingId();
		    }
			
			String resourceName = new ResourcesJdbcTemplate().search(newB.getResourceId()).getResourceName();

			try {
				response.getWriter().print((newB.toString()+","+resourceName+","+id));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
}
