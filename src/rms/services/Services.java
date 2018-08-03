package rms.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rms.queries.LoginQueries;


@Controller
public class Services {
	
	@RequestMapping(value="/")
	public String homeScreen() {
		return "login";
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
				return "redirect:dashboard";
			}
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("CHECKPOINT 3");
			return "redirect:/";
		}
		
		return "redirect:/";

	}
	
	@RequestMapping(value="/dashboard")
	public String dashBoard() {
		return "dashboard";
	}
	
}