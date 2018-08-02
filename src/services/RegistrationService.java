package services;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rms.dao.VisitorsJdbcTemplate;
import rms.model.Visitors;

@Controller
public class RegistrationService {
	
	@RequestMapping("/")
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
		newVisitor.setName(visitorName);
		newVisitor.setEmail(visitorEmail);
		newVisitor.setPhone(visitorPhone);
		newVisitor.setVisitingName(visitorVisitingName);
		newVisitor.setVisitPurpose(visitorVisitPurpose);
		newVisitor.setCompanyName(visitorCompanyName);
		
		newVisitor.setInTime(visitorInTime);
		newVisitor.setLocationId(visitorLocationID);
		newVisitor.setBadgeId(visitorBadgeID);
		newVisitor.setOutTime(visitorOutTime);
		newVisitor.setBookingId(visitorBookingID);
		newVisitor.setIdProof(visitorIDProof);
		newVisitor.setCheckedOut(visitorCheckedOut);
		
		
		System.out.println(newVisitor.getName());
		System.out.println(newVisitor.getCompanyName());
		
		return "errorPage";
		
		/*if(new VisitorsJdbcTemplate().insert(newVisitor) > 0) {
			
			return newVisitor.getName();
			//return "redirect:/showSuccessPage";
		} else {
			return "errorPagerrrr";
		}*/
	}

}
