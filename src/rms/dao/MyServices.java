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

@Controller
public class MyServices {
	
	@RequestMapping(value="/")
	public String homePage() {
		System.out.println("--------Hello service1 got executed--------");
		return "welcome";
	}
	
	@RequestMapping(value="/insertResource", method=RequestMethod.POST) 
	public String addResourceService(HttpServletRequest request, HttpServletResponse response) {
		String custName = request.getParameter("custName");
		String desc = request.getParameter("desc");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		String roomNum = request.getParameter("roomNum");
		int resourceTypeId = Integer.parseInt(request.getParameter("roomType"));
		//int isSupRoom = Integer.parseInt(request.getParameter("isSuperRoom"));
		
		
		Resources res = new Resources();
		res.setResourceName(custName);
		res.setResourceDescription(desc);
		res.setResourceRoomNumber(roomNum);
		res.setResourceTypeId(resourceTypeId);
		res.setLocationId(100001);
		res.setIsAvailable(1);
		res.setIsSuperRoom(0);
		res.setCapacity(capacity);
		
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
