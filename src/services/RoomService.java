package services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.xalan.internal.utils.FeatureManager.Feature;

import rms.model.FeaturesDropDown;
import rms.model.Features;
import rms.queries.FeatureQueries;

@Controller 
public class RoomService 
{
	
	@RequestMapping(value="/TODDD")
	public String defaultService(HttpServletRequest req, HttpServletResponse res, ModelMap map)
	{

		List<FeaturesDropDown> listOfFeatures = new FeatureQueries().getFeatureNameAndQuantityByResouceId();
		map.addAttribute("featData", listOfFeatures);
		

		
		return "RoomDropDown";  //view name
	}
	

	
 

}
