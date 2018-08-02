package services;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Services {
	
	@RequestMapping(value="/")
	public String homeScreen() {
		return "index";
	}
	
	@RequestMapping(value="/login")
	public String loginScreen() {
		return "index";
	}
	
	@RequestMapping(value="/dashboard")
	public String dashBoard() {
		return "dashboard";
	}
	
}