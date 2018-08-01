package rms.dao;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rms.model.Resources;
import rms.model.Features;
import rms.queries.UniqueResourcesAndLocations;

@Controller
public class MyServices {
	
	@RequestMapping(value="/")
	public String homePage(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("--------Hello service got executed--------");
		
		List<String> res1=new UniqueResourcesAndLocations().getDistinctResourceName();
        request.setAttribute("listPopRes", res1);
        
        Iterator it = res1.iterator();
        while(it.hasNext()) {
        	System.out.println(it.next());
        }
		return "addResource";
	}
	
	@RequestMapping(value="/insertResource", method=RequestMethod.POST) 
	public String addResourceService(HttpServletRequest request, HttpServletResponse response) {
		String resName = request.getParameter("resName");
		String desc = request.getParameter("desc");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		String roomNum = request.getParameter("roomNum");
		int resourceTypeId = Integer.parseInt(request.getParameter("roomType"));
		int isSupRoom = Integer.parseInt(request.getParameter("isSuperRoom"));
		
		
		//Number of features
		int numProjectorFeature = Integer.parseInt(request.getParameter("numResProjName"));
		int numPrinterFeature = Integer.parseInt(request.getParameter("numResPrintName"));
		int numVideoFeature = Integer.parseInt(request.getParameter("numResVidName"));
		int numTVFeature = Integer.parseInt(request.getParameter("numResTVName"));
		int numWhiteBoardFeature = Integer.parseInt(request.getParameter("numResWhiteBoardName"));
		int numFoodFeature = Integer.parseInt(request.getParameter("numResFoodName"));
		
		//Create resource object
		Resources res = new Resources();		
		res.setResourceName(resName);
		res.setResourceDescription(desc);
		res.setResourceRoomNumber(roomNum);
		res.setResourceTypeId(resourceTypeId);
		res.setLocationId(100001);
		res.setIsAvailable(0);
		res.setIsSuperRoom(isSupRoom);
		res.setCapacity(capacity);
		
		//Create feature object
		Features feat = new Features();
		feat.setFeatureTypeId(101);
		feat.setQuantity(numProjectorFeature);
		feat.setResourceId(res.getResourceId());
		
		System.out.println("num res proj: " + numProjectorFeature);
		System.out.println("num res printer: " + numPrinterFeature);
		System.out.println("num res video: " + numVideoFeature);
		System.out.println("num res tv: " + numTVFeature);
		System.out.println("num res whiteboard: " + numWhiteBoardFeature);
		System.out.println("num res food: " + numFoodFeature);	
		
		if (new ResourcesJdbcTemplate().insert(res)>0) {
			return "redirect:/showResourceForm";
		}
		return "errorPage";
	}

	@RequestMapping(value="/showResourceForm")
    public String showResourceFormService(ModelMap map) {
		System.out.println("resource");
        return "addResource";
    }
}
