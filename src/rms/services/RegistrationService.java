package rms.services;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rms.dao.VisitorsJdbcTemplate;
import rms.model.Visitors;

@Controller
public class RegistrationService {
	
	@RequestMapping("/registration")

	public String openRegistration() {
		return "RegistrationForm"; 
	}
	
	@RequestMapping(value="/registerVisitor")
	public String registerVisitorService(HttpServletRequest req, HttpServletResponse res) {
		
		//Set Strings for fields that user could fill in Register Form page
		String visitorName = req.getParameter("visitorName");
		String visitorEmail = req.getParameter("visitorEmail");
		String visitorPhone = req.getParameter("visitorPhone");
		String visitorVisitingName = req.getParameter("visitorVisitingName");
		String visitorVisitPurpose = req.getParameter("visitorVisitPurpose");
		String visitorCompanyName = req.getParameter("visitorCompanyName");
		
		//Setting rest of all the fields from Visitor table to null
		Timestamp visitorInTime = null;
		int visitorLocationID = 0;
		String visitorBadgeID = null;
		Timestamp visitorOutTime = null;
		int visitorBookingID = 0;
		String visitorIDProof = null;
		Timestamp visitorCheckedOut = null; //signout time
		
		//Create Visitor object to insert
		Visitors newVisitor = new Visitors();
		newVisitor.setVisitingName(visitorName);
		newVisitor.setEmail(visitorEmail);
		newVisitor.setPhone(visitorPhone);
		newVisitor.setVisitingName(visitorVisitingName);
		newVisitor.setVisitPurpose(visitorVisitPurpose);
		newVisitor.setCompanyName(visitorCompanyName);
		
		newVisitor.setCheckedInTime(visitorInTime);
		newVisitor.setLocationId(visitorLocationID);
		newVisitor.setBadgeId(visitorBadgeID);
		newVisitor.setBadgeId(visitorIDProof);
		newVisitor.setCheckedOutTime(visitorOutTime);
		System.out.println(newVisitor.getVisitingName());
		System.out.println(newVisitor.getCompanyName());
		
		return "errorPage";
		
		/*if(new VisitorsJdbcTemplate().insert(newVisitor) > 0) {
			
			return newVisitor.getName();
			//return "redirect:/showSuccessPage";
		} else {
			return "errorPagerrrr";
		}*/
	}
	
	@RequestMapping(value="/checkOut")
	public String insertAccountsService(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		String badgId = request.getParameter("bid");
		
		if(name == "Enter Name" || name == null) {
			if(new rms.queries.VisitorTracking().checkoutUsingBadgeID(badgId) > 0)
				return "ThankYou";
			else
					return "VisitorCheckOutForm";
		} else {
			String[] splited = name.split("\\s+");
			if(splited == null || splited.length != 2)
				return "VisitorCheckOutForm";
			else 
				if(new rms.queries.VisitorTracking().checkoutUsingFullName(name) > 0)
				return "ThankYou";
			else
				return "VisitorCheckOutForm";
		}
	}
	
	@RequestMapping(value="/checkOutForm")
	public String insertFormService(){
		return "VisitorCheckOutForm";
		
	}

}
