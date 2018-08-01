package dao;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

import dao.resources.Resources;
import dao.resources.ResourcesJdbcTemplate;

@Controller
public class MyServices {
	
	@RequestMapping(value="/")
	public String homePage() {
		System.out.println("--------Hello service1 got executed--------");
		return "welcome";
	}
	
//	@RequestMapping(value="/showAllAccount")
//	public String showAllAccountService(ModelMap map) {
//		List<BankAccount> allBankAccount = new MyJdbcTemplateApp().getAllAccount();
//		map.addAttribute("alldata",  allBankAccount);
//		return "allbankaccount";
//	}
//	
//	@RequestMapping(value="/deleteAccount/{accNo}") 
//	public String deleteAccountService(@PathVariable int accNo) {
//		System.out.println("delete executed-----------");
//		if (new MyJdbcTemplateApp().deleteAccount(accNo)>0) {
//			return "redirect:/showAllAccount";
//		}
//		else return "errorPage";
//	}
//	
//	@RequestMapping(value="/insertAccount") 
//	public String insertAccountService(HttpServletRequest request, HttpServletResponse response) {
//		String accName = request.getParameter("accName");
//		String accCity = request.getParameter("accCity");
//		String accPhone = request.getParameter("accPhone");
//		if (new MyJdbcTemplateApp().insertAccount(accName, accCity, accPhone)>0) {
//			return "redirect:/showAllAccount";
//		}
//		return "errorPage";
//	}
//	
//	@RequestMapping(value="/showInsertForm") 
//	public String showNewAccount() {
//		return "newAccount";
//	}
//	
//	@RequestMapping(value="updateAccount", method=RequestMethod.POST) 
//	public String updateAccountService(HttpServletRequest request, HttpServletResponse response) {	
//		int accNo = Integer.parseInt(request.getParameter("accNo"));
//		String accName = request.getParameter("accName");
//		String accCity = request.getParameter("accCity");
//		String accPhone = request.getParameter("accPhone");
//		int accBalance = Integer.parseInt(request.getParameter("accBalance"));
//		if (new MyJdbcTemplateApp().updateAccount(accNo, accCity)>0) {
//			return "redirect:/showAllAccount";
//		}
//		return "errorPage";
//	}
//	
//	@RequestMapping(value="showUpdateForm/{accno}")
//    public String showEditAccountService(@PathVariable int accno, ModelMap map) {
//        BankAccount ba = new MyJdbcTemplateApp().searchAccount(accno);
//        map.addAttribute("bankaccount", ba);
//        return "updateAccount";
//    }
	
	@RequestMapping(value="/insertResource", method=RequestMethod.POST) 
	public String addResourceService(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BLALALASDJSKJFSDKJFJSDF");

		
		String custName = request.getParameter("custName");
		String desc = request.getParameter("desc");
		//int capacity = Integer.parseInt(request.getParameter("capacity"));
		String roomNum = request.getParameter("roomNum");
		int resourceTypeId = Integer.parseInt(request.getParameter("roomType"));
		
		Resources res = new Resources();
		res.setResourceName(custName);
		res.setResourceDescription(desc);
		res.setResourceRoomNumber(roomNum);
		res.setResourceTypeId(resourceTypeId);
		res.setLocationId(100001);
		res.setIsAvailable(1);
		
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
