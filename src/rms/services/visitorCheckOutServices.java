package rms.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rms.dao.VisitorsJdbcTemplate;

@Controller
public class visitorCheckOutServices {
	@RequestMapping(value="/checkOut")
	public String insertAccountsService(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		String badgId = request.getParameter("bid");
		
		if(name == "Enter Name" || name == null) {
			if(new VisitorsJdbcTemplate().checkoutUsingBadgeID(badgId) > 0)
				return "redirect:/goodbuyService";
			else
					return "VisitorCheckOutForm";
		} else {
			String[] splited = name.split("\\s+");
			if(splited == null || splited.length != 2)
				return "VisitorCheckOutForm";
			else 
				if(new VisitorsJdbcTemplate().checkoutUsingFullName(name) > 0)
				return "redirect:/goodbuyService";
			else
				return "VisitorCheckOutForm";
		}
	}
	
	@RequestMapping(value="/checkOutForm")
	public String insertFormService(){
		return "VisitorCheckOutForm";
		
	}
}
