package services;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class service_demo {

	 @RequestMapping(method = RequestMethod.GET)
	   public String printHello(ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");
	      return "index";
	   }
	 
	 @RequestMapping(value="/search" , method = RequestMethod.GET)
	   public String search(ModelMap model) {
	      model.addAttribute("message", "Searching!");
	      
	      //get the first name and last name entered
	      String firstName;
	      String lastName; 
	      
	      //search name from database 
	      
	      return "index";
	   }
}