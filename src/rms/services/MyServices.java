package rms.services;


import java.util.Iterator;

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

import rms.model.Features;
import rms.dao.FeaturesJdbcTemplate;
import rms.model.Resources;
import rms.dao.BookingsJdbcTemplate;
import rms.dao.FeaturesJdbcTemplate;
import rms.dao.ResourceTypeJdbcTemplate;
import rms.dao.ResourcesJdbcTemplate;
import rms.queries.LoginQueries;
import rms.queries.UniqueResourcesAndLocations;
import rms.model.Bookings;
import rms.model.FeatureType;
import rms.model.Features;
import rms.model.ResourceType;
import rms.model.Resources;
import rms.queries.LoginQueries;

@Controller
public class MyServices {
	
	
	@RequestMapping(value="/")
	public String defaultService(){
		System.out.println("=-----------------helloo service got executed");
		return "login"; //view name
	}
	
	@RequestMapping(value="/loginOnUserName",method=RequestMethod.POST)
	public String loginOnUserName(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println(request.getParameter("userName"));
		System.out.println(request.getParameter("password"));
		
		
		LoginQueries login = new LoginQueries();
		
		System.out.println("CHECKPOINT 1");
		
		try {
			if(new LoginQueries().loginOnUserName(userName, password)!=null){
				System.out.println("CHECKPOINT 2");
				return "dashboard";
			}
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("CHECKPOINT 3");
			return "loginfailed";
		}
		 	
		return "loginfailed";

	}

	
	/**
	@RequestMapping(value="/hello")
	public String defaultService1(){
		System.out.println("=-----------------helloo service got executed");
		return "Welcome"; //view name
	}*/
	
	/*@RequestMapping(value="/AddSearchResources")
	public String searchAllResources(ModelMap map, HttpServletRequest request, HttpServletResponse response){
		System.out.println("=-----------------Search All Resources");
		System.out.println(request.getParameter("location"));
		//int locationId=Integer.parseInt(request.getParameter("location"));
		//System.out.println(locationId);
		List<dao.resources.Resources> allResources= new UniqueResourcesAndLocations().getResourcesByLocation(100001);
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
	*/
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
		String type = request.getParameter("type");
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
	   	if(type.equals("week")) {
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
		   		booking.setUserId(101);
		   		booking.setDescription("An event");
		   	
		   		
		   		System.out.println(booking);
		   		new BookingsJdbcTemplate().insert(booking);
		   	}
	   	} else {
	   		// Parse the repeat array
	   		String str = request.getParameter("repeats");
	   		str = str.substring(1, str.length() - 1);
	   		String[] split = str.split(",");
	   		
	   		// Make a booking for each day of the week checked
	   		Calendar cal = Calendar.getInstance();
	   		for(int i = 0; i < split.length; i++){
	   			if(Boolean.parseBoolean(split[i])){
	   				
	   				// Get the start timestamp
			   		cal.setTimeInMillis(date1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		   			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			   		cal.add(Calendar.DAY_OF_YEAR, i);
			   		Timestamp start = new Timestamp(cal.getTimeInMillis());
			   		
			   		// Get the stop time stamp
			   		cal.setTimeInMillis(date2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		   			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			   		cal.add(Calendar.DAY_OF_YEAR, i);
			   		Timestamp stop = new Timestamp(cal.getTimeInMillis());
			   		
			   		Bookings booking = new Bookings();
			   		booking.setIsActive(1);
			   		booking.setBookedStartTime(start);
			   		booking.setBookedEndTime(stop);
			   		booking.setResourceId(Integer.parseInt(resourceId));
			   		booking.setUserId(101);
			   		booking.setDescription("An event");
			   		System.out.println(booking);
			   		new BookingsJdbcTemplate().insert(booking);
	   			}
	   		}
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
		
		//get resource types instead of all resources
		//List<String> res=new UniqueResourcesAndLocations().getDistinctResourceName();
		List<ResourceType> res=new ResourceTypeJdbcTemplate().getAll();

		request.setAttribute("listRes", res);
		
		//for printing all the resources at the bottom of view. 
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
		List<Resources> allResources= new UniqueResourcesAndLocations().getResourcesByLocationAndResourceType(locationId, resourceTypeId);
		map.addAttribute("alldata", allResources);
		System.out.println("=-----------------helloo service got executed");
		return "FilterResources"; //view name
	}
	
	@RequestMapping(value="/insertResource", method=RequestMethod.POST) 
	public String addResourceService(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("1");
		String desc = request.getParameter("desc");
		System.out.println("2");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		System.out.println("3");
		String roomNum = request.getParameter("roomNum");
		System.out.println("4");
		int resourceTypeId = Integer.parseInt(request.getParameter("resources"));
		System.out.println("5");
		int locId = Integer.parseInt(request.getParameter("location"));
		System.out.println("6");
		int isSupRoom = Integer.parseInt(request.getParameter("isSuperRoom"));
		System.out.println("7");
		List<String> res2=new UniqueResourcesAndLocations().ResourceTypeName(Integer.parseInt(request.getParameter("resources")));
		System.out.println("8");
		request.setAttribute("typeName", res2);
        
		//Number of features
		int numProjectorFeature = Integer.parseInt(request.getParameter("numResProjName"));
		int numPrinterFeature = Integer.parseInt(request.getParameter("numResPrintName"));
		int numVideoFeature = Integer.parseInt(request.getParameter("numResVidName"));
		int numTVFeature = Integer.parseInt(request.getParameter("numResTVName"));
		int numWhiteBoardFeature = Integer.parseInt(request.getParameter("numResWhiteBoardName"));
		int numFoodFeature = Integer.parseInt(request.getParameter("numResFoodName"));
		
		//Create resource object
		Resources res = new Resources();		
		res.setResourceName(res2.get(0));
		res.setResourceDescription(desc);
		res.setResourceRoomNumber(roomNum);
		res.setResourceTypeId(resourceTypeId);
		res.setLocationId(locId);
		res.setIsAvailable(0); 
		res.setIsSuperRoom(isSupRoom);
		res.setCapacity(capacity);
		
		
		ResourcesJdbcTemplate resTemp = new ResourcesJdbcTemplate();
		FeaturesJdbcTemplate featTemp = new FeaturesJdbcTemplate();
		
		//Add a new resource
		resTemp.insert(res);
		
		List<Integer> resourceIdTest=new UniqueResourcesAndLocations().getMostRecentResourceId();
		System.out.println(resourceIdTest.get(0));
		
		//Create feature object
		if (numProjectorFeature>0) {
			Features featProj = new Features();
			featProj.setFeatureTypeId(101);
			featProj.setQuantity(numProjectorFeature);
			featProj.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featProj);
			System.out.println("projector feat inserted");
		}
		if (numPrinterFeature>0) {
			Features featPrint = new Features();
			featPrint.setFeatureTypeId(101);
			featPrint.setQuantity(numPrinterFeature);
			featPrint.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featPrint);
			System.out.println("printer feat inserted");
		}
		if (numVideoFeature>0) {
			Features featVid = new Features();
			featVid.setFeatureTypeId(101);
			featVid.setQuantity(numVideoFeature);
			featVid.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featVid);
			System.out.println("video feat inserted");
		}
		if (numTVFeature>0) {
			Features featTV = new Features();
			featTV.setFeatureTypeId(101);
			featTV.setQuantity(numTVFeature);
			featTV.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featTV);
			System.out.println("tv feat inserted");
		}
		if (numWhiteBoardFeature>0) {
			Features featWhiteBoard = new Features();
			featWhiteBoard.setFeatureTypeId(101);
			featWhiteBoard.setQuantity(numWhiteBoardFeature);
			featWhiteBoard.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featWhiteBoard);
			System.out.println("whiteboard feat inserted");
		}
		if (numFoodFeature>0) {
			Features featFood = new Features();
			featFood.setFeatureTypeId(101);
			featFood.setQuantity(numFoodFeature);
			featFood.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featFood);
			System.out.println("food feat inserted");
		}
		
		return "redirect:/AddSearchResources1";
	}


	
}
