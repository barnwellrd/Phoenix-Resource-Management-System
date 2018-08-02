package services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rms.dao.ResourcesJdbcTemplate;
import rms.queries.UniqueResourcesAndLocations;
import rms.model.Resources;

@Controller
public class MyServices {
	
	@RequestMapping(value="/")
	public String defaultService(){
		System.out.println("=-----------------helloo service got executed");
		return "Welcome"; //view name
	}
	
	/*@RequestMapping(value="/AddSearchResources")
	public String searchAllResources(ModelMap map, HttpServletRequest request, HttpServletResponse response){
		System.out.println("=-----------------Search All Resources");
		System.out.println(request.getParameter("location"));
		//int locationId=Integer.parseInt(request.getParameter("location"));
		//System.out.println(locationId);
		List<dao.resources.Resources> allResources= new UniqueResourcesAndLocations().getResourcesByLocation(100001);
		map.addAttribute("alldata", allResources);
		System.out.println("=-----------------helloo service got executed");
		return "AddSearchResources"; //view name
	}*/
	@RequestMapping(value="/LocationResources")
	public String searchLocationResources(ModelMap map, HttpServletRequest request, HttpServletResponse response){
		System.out.println("=-----------------Search Location Resources");
		//System.out.println(request.getParameter("location")+"-----"+ request.getParameter("resources"));
		int locationId=Integer.parseInt(request.getParameter("location"));
		int resourceTypeId=Integer.parseInt(request.getParameter("resources"));
		System.out.println(locationId+" l "+resourceTypeId);
		List<	Resources> allResources= new UniqueResourcesAndLocations().getResourcesByLocationAndResourceType(locationId, resourceTypeId);
		map.addAttribute("alldata", allResources);
		System.out.println("=-----------------helloo service got executed");
		return "FilterResources"; //view name
	}
	@RequestMapping(value="/AddSearchResources1")
	public String searchAllResources1(ModelMap map,HttpServletRequest request, HttpServletResponse response){
		System.out.println("=-----------------searchAllResources1");
		List<String> loc=new UniqueResourcesAndLocations().getLocationAndCity();
		request.setAttribute("listCategory", loc);
		List<String> res=new UniqueResourcesAndLocations().getDistinctResourceName();
		request.setAttribute("listRes", res);
		List<Resources> allResources= new UniqueResourcesAndLocations().getResourcesByLocation(100001);
		map.addAttribute("alldata", allResources);
		System.out.println("=-----------------helloo service got executed");
		return "AddSearchResources"; //view name
	}
	
}
