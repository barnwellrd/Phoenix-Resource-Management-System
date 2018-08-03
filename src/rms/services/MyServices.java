package rms.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rms.dao.BookingsJdbcTemplate;
import rms.dao.FeaturesJdbcTemplate;
import rms.dao.ResourceTypeJdbcTemplate;
import rms.dao.ResourcesJdbcTemplate;
import rms.queries.LoginQueries;
import rms.queries.UniqueResourcesAndLocations;
import rms.model.Bookings;
import rms.model.FeatureType;
import rms.model.Features;
import rms.model.Resources;

@Controller
public class MyServices {
	
	@RequestMapping(value="/")
	public String homeScreen() {
		return "login";
	}
	
	@RequestMapping(value="/logout")
	public String logout() {
		return "login";
	}
	
	@RequestMapping(value="/dashboard")
	public String dashBoard() {
				
		return "dashboard";
	}

	@RequestMapping(value="/loginOnUserName",method=RequestMethod.POST)
	public String loginOnUserName(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

        request.getSession().setAttribute("userName",userName);
        request.getSession().setAttribute("pass",password);
        
		System.out.println(request.getParameter("userName"));
		System.out.println(request.getParameter("password"));
		
		LoginQueries login = new LoginQueries();
		
		System.out.println("CHECKPOINT 1");
		
		try {
			if(new LoginQueries().loginOnUserName(userName, password)!=null){
				System.out.println("CHECKPOINT 2");
				return "redirect:/dashboard";
			}
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("CHECKPOINT 3");
			return "redirect:/";
		}
		
		return "redirect:/";

	}
	
	@RequestMapping(value="/deleteEvent")
	public void deleteEvent(HttpServletRequest request, HttpServletResponse response){
		
		int id = Integer.parseInt(request.getParameter("bookingId"));
		new BookingsJdbcTemplate().delete(id);
		
	}
	
	@RequestMapping(value="/updateEvent")
	public void updateEvent(HttpServletRequest request, HttpServletResponse response){
		
		
		// Read data from ajax call
	    String date = request.getParameter("date");
		String timeTo = request.getParameter("timeTo");
		String timeFrom = request.getParameter("timeFrom");
		int id = Integer.parseInt(request.getParameter("bookingId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		int resourceId = new BookingsJdbcTemplate().search(id).getResourceId();

		System.out.println(date);
		System.out.println(timeTo);
		
	   	DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String dates1 = date+" "+timeFrom;
		String dates2 = date+" "+timeTo;
		
		//translate the calendar date into a date for the database. 
		LocalDateTime date1 = LocalDateTime.parse(dates1,format);
	   	LocalDateTime date2 = LocalDateTime.parse(dates2,format);
	   	Timestamp setter1 = new Timestamp(date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
	   	Timestamp setter2 = new Timestamp(date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
	   			   		
	   		Bookings booking = new Bookings();
	   	booking.setIsActive(1);
	   	booking.setBookedStartTime(setter1);
	   	booking.setBookedEndTime(setter2);
	  	booking.setResourceId(resourceId);
	  	booking.setBookingId(id);
	   	booking.setUserId(userId);
	   	booking.setDescription("An event");	 
		
		new BookingsJdbcTemplate().update(booking);
		
	}
	
	@RequestMapping(value="/addEvent")
	public void addEvent(HttpServletRequest request, HttpServletResponse response) {
		// Read data from ajax call
	    String date = request.getParameter("date");
		String timeTo = request.getParameter("timeTo");
		String timeFrom = request.getParameter("timeFrom");
		String resourceId = request.getParameter("resourceId");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		System.out.println(date);
		System.out.println(timeTo);
		
	   	DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String dates1 = date+" "+timeFrom;
		String dates2 = date+" "+timeTo;
		
		//translate the calendar date into a date for the database. 
		LocalDateTime date1 = LocalDateTime.parse(dates1,format);
	   	LocalDateTime date2 = LocalDateTime.parse(dates2,format);
	   	Timestamp setter1 = new Timestamp(date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
	   	Timestamp setter2 = new Timestamp(date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

	   	
	   	// Check the number of repeats
	   	int repeats = Integer.parseInt(request.getParameter("repeats"));
	   	
	   	System.out.println("Weekly repeats: " + repeats);
	   	for(int i = 0; i < repeats + 1; i++) {
	   		// Get the start timestamp
	   		Calendar cal = Calendar.getInstance();
	   		cal.setTimeInMillis(date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
	   		cal.add(Calendar.WEEK_OF_MONTH, i);
	   		Timestamp start = new Timestamp(cal.getTimeInMillis());
	   		
	   		// Get the stop time stamp
	   		cal.setTimeInMillis(date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
	   		cal.add(Calendar.WEEK_OF_MONTH, i);
	   		Timestamp stop = new Timestamp(cal.getTimeInMillis());
	   			   		
	   		Bookings booking = new Bookings();
	   		booking.setIsActive(1);
	   		booking.setBookedStartTime(start);
	   		booking.setBookedEndTime(stop);
	   		booking.setResourceId(Integer.parseInt(resourceId));
	   		booking.setUserId(userId);
	   		booking.setDescription("An event");
	   	
	   		
	   		System.out.println(booking);
	   		new BookingsJdbcTemplate().insert(booking);
	   	}	   			
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
	
	@RequestMapping(value="/getBookingsAsTableByResourceId")
	public void getBookingsAsTableByResourceId(HttpServletRequest request, HttpServletResponse response) {
		
		String resId = request.getParameter("resourceId");
		
		int resourceId = Integer.parseInt(resId);
		List<Bookings> allBookings = new BookingsJdbcTemplate().getAllByResourceId(resourceId);
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
	
	@RequestMapping(value="/getBookingsAsTableByType")
	public void getBookingsAsTableByType(HttpServletRequest request, HttpServletResponse response) {
		
		String resId = request.getParameter("resourceTypeId");
		System.out.println(resId);
		int resourceTypeId = Integer.parseInt(request.getParameter("resourceTypeId"));
		List<Bookings> allBookings = new BookingsJdbcTemplate().getAllByResourceType(resourceTypeId);
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
	
	@RequestMapping(value="/booking")
	public String booking() {
		
		return "booking";
	}
	
	@RequestMapping(value="/types")
	public String pickResource(ModelMap map){
		map.addAttribute("types", new ResourceTypeJdbcTemplate().getAll());
		return "types";
	}
	
	@RequestMapping(value="/resources", method=RequestMethod.POST)
	public String pickRoom(HttpServletRequest request, HttpServletRequest response){
		// Get rooms of a given type
		int type = Integer.parseInt(request.getParameter("type"));
		List<Resources> roomsOfThisType = new ResourcesJdbcTemplate().resourcesByResourceType(type);
		
		// Map rooms to their resources
		Map<Resources, List<FeatureType>> roomsAndFeatures = new HashMap<Resources, List<FeatureType>>();
		Map<FeatureType, Features> featuresAndQuantities = new HashMap<FeatureType, Features>(); 
		for(Resources room: roomsOfThisType){
			List<FeatureType> features = new ResourcesJdbcTemplate().getFeatures(room.getResourceId());
			roomsAndFeatures.put(room, features);
			for(FeatureType feature: features){
				featuresAndQuantities.put(feature, new FeaturesJdbcTemplate().searchByFeatureTypeIdAndResourceId(feature.getFeatureTypeId(), room.getResourceId()));
			}
		}
				
		// Pass the maps and the rooms
		response.setAttribute("type", type);
		response.setAttribute("rooms", roomsOfThisType);
		response.setAttribute("featureMap", roomsAndFeatures);
		response.setAttribute("quantityMap", featuresAndQuantities);

		return "rooms";
	}
	
	@RequestMapping(value="/roomDesc", method=RequestMethod.POST)
	public String displayRoom(HttpServletRequest request, HttpServletRequest response) {
		// Find the room
		int roomID = Integer.parseInt(request.getParameter("room"));
		Resources room = new ResourcesJdbcTemplate().search(roomID);
				
		Map<FeatureType, Features> featuresAndQuantities = new HashMap<FeatureType, Features>(); 

		List<FeatureType> features = new ResourcesJdbcTemplate().getFeatures(room.getResourceId());

		for(FeatureType feature: features){
				featuresAndQuantities.put(feature, new FeaturesJdbcTemplate().searchByFeatureTypeIdAndResourceId(feature.getFeatureTypeId(), room.getResourceId()));
		}
		
		// Pass the room
		response.setAttribute("room", room);
		response.setAttribute("featureMap", featuresAndQuantities);

		return "displayRoom";
	}
	
	@RequestMapping(value="/AddSearchResources")
	public String searchAllResources1(ModelMap map,HttpServletRequest request, HttpServletResponse response){
		System.out.println("=-----------------searchAllResources1");
		List<String> loc=new UniqueResourcesAndLocations().getLocationAndCity();
		request.setAttribute("listCategory", loc);
		List<String> res=new UniqueResourcesAndLocations().getDistinctResourceName();
		request.setAttribute("listRes", res);
		List<Resources> allResources= new UniqueResourcesAndLocations().getResourcesByLocation(100001);
		map.addAttribute("alldata", allResources);
		System.out.println("=-----------------helloo service got executed");
		return "AddSearchResources"; //view name
	}
	
	@RequestMapping(value="/LocationResources")
	public String searchLocationResources(ModelMap map, HttpServletRequest request, HttpServletResponse response){
		System.out.println("=-----------------Search Location Resources");
		//System.out.println(request.getParameter("location")+"-----"+ request.getParameter("resources"));
		int locationId=Integer.parseInt(request.getParameter("location"));
		int resourceTypeId=Integer.parseInt(request.getParameter("resources"));
		System.out.println(locationId+" l "+resourceTypeId);
		List<	Resources> allResources= new UniqueResourcesAndLocations().getResourcesByLocationAndResourceType(locationId, resourceTypeId);
		map.addAttribute("alldata", allResources);
		System.out.println("=-----------------helloo service got executed");
		return "FilterResources"; //view name
	}
	

	
}
