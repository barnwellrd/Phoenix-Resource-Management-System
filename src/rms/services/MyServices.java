package rms.services;


import java.util.Iterator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rms.model.Features;
import rms.dao.FeaturesJdbcTemplate;
import rms.model.Resources;
import rms.dao.ResourcesJdbcTemplate;
import rms.queries.UniqueResourcesAndLocations;
import rms.queries.LoginQueries;

@Controller
public class MyServices {
	
	
	@RequestMapping(value="/")
	public String defaultService(){
		System.out.println("=-----------------helloo service got executed");
		return "login"; //view name
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
				return "dashboard";
			}
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("CHECKPOINT 3");
			return "loginfailed";
		}
		 	
		return "loginfailed";

	}

	
	/**
	@RequestMapping(value="/hello")
	public String defaultService1(){
		System.out.println("=-----------------helloo service got executed");
		return "Welcome"; //view name
	}*/
	
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
		List<Resources> allResources= new UniqueResourcesAndLocations().getResourcesByLocationAndResourceType(locationId, resourceTypeId);
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
	
	@RequestMapping(value="/insertResource", method=RequestMethod.POST) 
	public String addResourceService(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("1");
		String desc = request.getParameter("desc");
		System.out.println("2");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		System.out.println("3");
		String roomNum = request.getParameter("roomNum");
		System.out.println("4");
		int resourceTypeId = Integer.parseInt(request.getParameter("resources"));
		System.out.println("5");
		int locId = Integer.parseInt(request.getParameter("location"));
		System.out.println("6");
		int isSupRoom = Integer.parseInt(request.getParameter("isSuperRoom"));
		System.out.println("7");
		List<String> res2=new UniqueResourcesAndLocations().ResourceTypeName(Integer.parseInt(request.getParameter("resources")));
		System.out.println("8");
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
		
		return "redirect:/AddSearchResources1";
	}


	
}
