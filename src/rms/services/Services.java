package rms.services;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rms.model.FeatureNameAndQuantity;
import rms.queries.FeatureQueries;


@Controller
public class Services {
	
	@RequestMapping(value="/")
	public String homeScreen() {
		return "login";
	}
	
	@RequestMapping(value="/dashboard")
	public String dashBoard() {
		return "dashboard";
	}
	
}