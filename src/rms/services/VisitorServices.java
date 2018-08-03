package rms.services;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rms.dao.VisitorsJdbcTemplate;
import rms.model.Visitors;

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
	
	@RequestMapping(value="Visitor/RegisterVisitor")
	public String registerVisitorService(HttpServletRequest req, HttpServletResponse res) {
		
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
		
		return "visitorErrorPage";
		
		/*if(new VisitorsJdbcTemplate().insert(newVisitor) > 0) {
			
			return newVisitor.getName();
			//return "redirect:/showSuccessPage";
		} else {
			return "errorPagerrrr";
		}*/
	}

	
	@RequestMapping(value="Visitor/CheckOut")
	public String insertAccountsService(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		String badgId = request.getParameter("bid");
		
		if(name == "Enter Name" || name == null) {
			if(new rms.queries.VisitorTracking().checkoutUsingBadgeID(badgId) > 0)
				return "visitorHome";
			else
					return "visitorCheckOutForm";
		} else {
			String[] splited = name.split("\\s+");
			if(splited == null || splited.length != 2)
				return "visitorCheckOutForm";
			else 
				if(new rms.queries.VisitorTracking().checkoutUsingFullName(name) > 0)
				return "visitorHome";
			else
				return "visitorCheckOutForm";
		}
	}
	
	@RequestMapping(value="Visitor/CheckOutForm")
	public String insertFormService(){
		return "visitorCheckOutForm";
		
	}
}
