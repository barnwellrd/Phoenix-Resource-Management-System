package rms.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import rms.dao.BookingsJdbcTemplate;
import rms.dao.VisitorsJdbcTemplate;
import rms.model.Visitors;
import rms.queries.VisitorTracking;

@Controller
public class VisitorServices {
	
	@RequestMapping(value="/Visitor/Home")
	public String homeScreen() {
		return "visitorHome";
	}

	@RequestMapping(value="/Visitor/Registration")
	public String openRegistration() {
		return "visitorRegistrationForm"; 
	}
	
	@RequestMapping(value="/Visitor/RegisterVisitor")
	public String registerVisitorService(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //
		
		//Set Strings for fields that user could fill in Register Form page
		String visitorFirstName = req.getParameter("visitorFirstName");
		String visitorLastName = req.getParameter("visitorLastName");
		String visitorEmail = req.getParameter("visitorEmail");
		String visitorPhone = req.getParameter("visitorPhone");
		String visitorVisitingName = req.getParameter("visitorVisitingName");
		String visitorVisitPurpose = req.getParameter("visitorVisitPurpose");
		String visitorCompanyName = req.getParameter("visitorCompanyName");
		if (visitorCompanyName.equals("Other")) {
			visitorCompanyName = req.getParameter("otherCompanyName");
		}
		Timestamp visitorCheckedInTime = timestamp; //
		int visitorLocationID = 100001;
		
		model.addAttribute("full_name", (visitorFirstName + " " + visitorLastName));
		//Setting rest of all the fields from Visitor table to null
		String visitorBadgeID = null;
		int visitorIDProof = 0;
		Timestamp visitorCheckedOutTime = null; //signout time
		int visitorHasCheckedOut = 0; //signout time
		
		
		//Create Visitor object to insert
		Visitors newVisitor = new Visitors();
		newVisitor.setFirstName(visitorFirstName);
		newVisitor.setLastName(visitorLastName);
		newVisitor.setEmail(visitorEmail);
		newVisitor.setPhone(visitorPhone);
		newVisitor.setVisitingName(visitorVisitingName);
		newVisitor.setVisitPurpose(visitorVisitPurpose);
		newVisitor.setCompanyName(visitorCompanyName);
		
		newVisitor.setCheckedInTime(visitorCheckedInTime);
		newVisitor.setLocationId(visitorLocationID);
		newVisitor.setBadgeId(visitorBadgeID);
		newVisitor.setCheckedOutTime(visitorCheckedOutTime);
		newVisitor.setHasIdProof(visitorIDProof);
		newVisitor.setHasCheckedOut(visitorHasCheckedOut);
		
		
		System.out.println(newVisitor.getFirstName());
		System.out.println(newVisitor.getLastName());
		System.out.println(newVisitor.getCompanyName());
		
		
		VisitorsJdbcTemplate vjt = new VisitorsJdbcTemplate();
		vjt.insert(newVisitor);
		
		return "visitorWelcome";
		
		/*if(new VisitorsJdbcTemplate().insert(newVisitor) > 0) {
			
			return newVisitor.getName();
			//return "redirect:/showSuccessPage";
		} else {
			return "errorPagerrrr";
		}*/
	}

	
	@RequestMapping(value="/Visitor/CheckOut")
	public String insertAccountsService(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		String phone = request.getParameter("phone");
		String badgId = request.getParameter("bid");
		
		if(phone.equals("")) {
			if(new rms.queries.VisitorTracking().checkoutUsingBadgeID(badgId) > 0)
			{
				String name = new VisitorTracking().getVisitorUsingBadgeId(badgId);
				model.addAttribute("full_name", name);
				return "visitorGoodbye";
			}
			else
				return "visitorCOForm";
		} else {
			if(new VisitorTracking().checkoutUsingPhone(phone) > 0)
			{
				String name = new VisitorTracking().getVisitorUsingPhone(phone);
				model.addAttribute("full_name", name);
				return "visitorGoodbye";
			}
			else
				return "visitorCOForm";
		}
	}
	
	@RequestMapping(value="/Visitor/COForm")
	public String insertFormService(){
		return "visitorCOForm";
		
	}
	
	@RequestMapping(value="/Visitor/Admin")
	public String visitorAdminService(ModelMap map){
		List<Visitors> dailyVisitors= new VisitorTracking().getVisitorsFromToday();
		map.addAttribute("alldata", dailyVisitors);
		return "visitorAdminView";
		
	}
	
	@RequestMapping(value ="/Visitor/RangeSearch")
	public String visitorRangeSearch(HttpServletRequest request, HttpServletResponse response, ModelMap map) {

		String fromDay = request.getParameter("fromDay");
		String toDay = request.getParameter("toDay");
		 
		String pattern = "yyyy-MM-dd"; //what we have
		String pattern2 = "MM-dd-yyyy";	// what we want
		
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		SimpleDateFormat format2 = new SimpleDateFormat(pattern2);
		Date datef= new Date(); // new date
		Date datet= new Date(); 
		try {
			datef = format.parse(fromDay);// convert what we have from string to date
			datet = format.parse(toDay); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		fromDay = format2.format(datef); // convert date to string in the format that we want
		toDay = format2.format(datet); // convert date to string in the format that we want
		
		List<Visitors> rangeVisitors=new VisitorTracking().getVisitorsFromRange(fromDay,toDay);
		map.addAttribute("alldata", rangeVisitors);
		return "visitorAdminView";
	}
	
	@RequestMapping(value="/Visitor/AdminCO/{visitor_id}")
	public String visitorsAdminCheckOutService(@PathVariable String visitor_id){
		new VisitorTracking().checkoutUsingVisitorId(visitor_id);
			return "redirect:/Visitor/Admin";
	}
	
}
