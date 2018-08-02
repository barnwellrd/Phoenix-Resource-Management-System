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
		
		
		List<String> res1=new UniqueResourcesAndLocations().getDistinctResourceIdAndName();
        request.setAttribute("listPopRes", res1);
        
        List<String> locIdOptions=new UniqueResourcesAndLocations().getLocationAndCity();
        request.setAttribute("locPopRes", locIdOptions);
        
		return "addResource";
	}
	
	@RequestMapping(value="/insertResource", method=RequestMethod.POST) 
	public String addResourceService(HttpServletRequest request, HttpServletResponse response) {
		String desc = request.getParameter("desc");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		String roomNum = request.getParameter("roomNum");
		int resourceTypeId = Integer.parseInt(request.getParameter("resssTypeId"));
		int locId = Integer.parseInt(request.getParameter("resLoc"));
		int isSupRoom = Integer.parseInt(request.getParameter("isSuperRoom"));
		List<String> res2=new UniqueResourcesAndLocations().ResourceTypeName(Integer.parseInt(request.getParameter("resssTypeId")));
        request.setAttribute("typeName", res2);
		
		//Number of features
		int numProjectorFeature = Integer.parseInt(request.getParameter("numResProjName"));
		int numPrinterFeature = Integer.parseInt(request.getParameter("numResPrintName"));
		int numVideoFeature = Integer.parseInt(request.getParameter("numResVidName"));
		int numTVFeature = Integer.parseInt(request.getParameter("numResTVName"));
		int numWhiteBoardFeature = Integer.parseInt(request.getParameter("numResWhiteBoardName"));
		int numFoodFeature = Integer.parseInt(request.getParameter("numResFoodName"));
		
		//Create resource object
		Resources res = new Resources();		
		res.setResourceName(res2.get(0));
		res.setResourceDescription(desc);
		res.setResourceRoomNumber(roomNum);
		res.setResourceTypeId(resourceTypeId);
		res.setLocationId(locId);
		res.setIsAvailable(0); 
		res.setIsSuperRoom(isSupRoom);
		res.setCapacity(capacity);
		
		
		ResourcesJdbcTemplate resTemp = new ResourcesJdbcTemplate();
		FeaturesJdbcTemplate featTemp = new FeaturesJdbcTemplate();
		
		//Add a new resource
		resTemp.insert(res);
		
		List<Integer> resourceIdTest=new UniqueResourcesAndLocations().getMostRecentResourceId();
		System.out.println(resourceIdTest.get(0));
		
		//Create feature object
		if (numProjectorFeature>0) {
			Features featProj = new Features();
			featProj.setFeatureTypeId(101);
			featProj.setQuantity(numProjectorFeature);
			featProj.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featProj);
			System.out.println("projector feat inserted");
		}
		if (numPrinterFeature>0) {
			Features featPrint = new Features();
			featPrint.setFeatureTypeId(101);
			featPrint.setQuantity(numPrinterFeature);
			featPrint.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featPrint);
			System.out.println("printer feat inserted");
		}
		if (numVideoFeature>0) {
			Features featVid = new Features();
			featVid.setFeatureTypeId(101);
			featVid.setQuantity(numVideoFeature);
			featVid.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featVid);
			System.out.println("video feat inserted");
		}
		if (numTVFeature>0) {
			Features featTV = new Features();
			featTV.setFeatureTypeId(101);
			featTV.setQuantity(numTVFeature);
			featTV.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featTV);
			System.out.println("tv feat inserted");
		}
		if (numWhiteBoardFeature>0) {
			Features featWhiteBoard = new Features();
			featWhiteBoard.setFeatureTypeId(101);
			featWhiteBoard.setQuantity(numWhiteBoardFeature);
			featWhiteBoard.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featWhiteBoard);
			System.out.println("whiteboard feat inserted");
		}
		if (numFoodFeature>0) {
			Features featFood = new Features();
			featFood.setFeatureTypeId(101);
			featFood.setQuantity(numFoodFeature);
			featFood.setResourceId(resourceIdTest.get(0));
			featTemp.insert(featFood);
			System.out.println("food feat inserted");
		}
		
		return "redirect:/";
	}

	@RequestMapping(value="/showResourceForm")
    public String showResourceFormService(ModelMap map) {
		System.out.println("resource");
        return "addResource";
    }
}
