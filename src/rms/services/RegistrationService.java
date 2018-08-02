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
	

	@RequestMapping(value="/registration")
	public String openRegistration() {
		return "RegistrationForm"; 
	}
	
	@RequestMapping(value="/registerVisitor")
	public String registerVisitorService(HttpServletRequest req, HttpServletResponse res) {
		
		//Set Strings for fields that user could fill in Register Form page
		String visitorFirstName = req.getParameter("visitorFirstName");
		String visitorLastName = req.getParameter("visitorLastName");
		String visitorEmail = req.getParameter("visitorEmail");
		String visitorPhone = req.getParameter("visitorPhone");
		String visitorVisitingName = req.getParameter("visitorVisitingName");
		String visitorVisitPurpose = req.getParameter("visitorVisitPurpose");
		String visitorCompanyName = req.getParameter("visitorCompanyName");
		
		//Setting rest of all the fields from Visitor table to null
		Timestamp visitorCheckedInTime = null;
		int visitorLocationID = 0;
		String visitorBadgeID = null;
		Timestamp visitorCheckedOutTime = null;
		int visitorBookingID = 0;
		int visitorIDProof = 0;
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
		
		return "errorPage";
		
		/*if(new VisitorsJdbcTemplate().insert(newVisitor) > 0) {
			
			return newVisitor.getName();
			//return "redirect:/showSuccessPage";
		} else {
			return "errorPagerrrr";
		}*/
	}

}
