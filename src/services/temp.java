package services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class temp
{
	@RequestMapping(value="/")
	public String defaultService()
	{
		return "RoomDropDown";  //view name
	}

}
