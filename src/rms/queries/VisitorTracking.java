package rms.queries;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.VisitorsMapper;
import rms.model.Visitors;

public class VisitorTracking {
	private ApplicationContext context;
	private JdbcTemplate jtemp;
	
	public VisitorTracking() {

        this.context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.jtemp = (JdbcTemplate)context.getBean("jt");
	}
	
	public int checkoutUsingFullName(String fullName){
		return jtemp.update("UPDATE visitors SET has_checked_out = 1, checked_out_time = CURRENT_TIMESTAMP WHERE upper(first_name||' '||last_name) = upper(?)",fullName);
	}
	
	public int checkoutUsingFirstAndLast(String firstName, String lastName){
		return jtemp.update("UPDATE visitors SET has_checked_out = 1, checked_out_time = CURRENT_TIMESTAMP WHERE first_name = ? AND last_name = ?",firstName,lastName);
	}
	
	public int checkoutUsingBadgeID(String badgeID){
		return jtemp.update("UPDATE visitors SET has_checked_out = 1, checked_out_time = CURRENT_TIMESTAMP WHERE badge_id = ?",badgeID);
	}
	
	public int checkoutUsingPhone(String phone){
		return jtemp.update("UPDATE visitors SET has_checked_out = 1, checked_out_time = CURRENT_TIMESTAMP WHERE phone = ? AND has_checked_out = 0",phone);
	}
	
	public String getVisitorUsingBadgeId(String badge_id) {
		return jtemp.queryForObject("SELECT first_name||' '||last_name FROM visitors WHERE badge_id = ?", String.class, badge_id);
	}
	
	public int checkoutUsingVisitorId(String visitor_id){
		return jtemp.update("UPDATE visitors SET has_checked_out = 1, checked_out_time = CURRENT_TIMESTAMP WHERE visitor_id = ? and has_checked_out = 0",visitor_id);
	}
	public String getVisitorUsingPhone(String phone) {
		return jtemp.queryForObject("SELECT unique first_name||' '||last_name FROM visitors WHERE phone = ? and CHECKED_OUT_TIME >= (systimestamp-1/24)",String.class, phone);
	}
	public List<Visitors> getVisitorsFromToday()
	{
		//Gets visitors from the current day.
		String myTime = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		return jtemp.query("SELECT * FROM visitors WHERE trunc(CHECKED_IN_TIME) = TO_DATE(?,'mm/dd/yyyy')", new VisitorsMapper(), myTime);
	}
	
	public List<Visitors> getVisitorsFromDay(String day)
	{
		//Gets the visitors from a selected day, passed as a string.
		//The string must be passed in the format 'mm/dd/yyyy', i.e. '11/27/2018'.
		return jtemp.query("SELECT * FROM visitors WHERE trunc(CHECKED_IN_TIME) = TO_DATE(?,'mm/dd/yyyy')", new VisitorsMapper(), day);
	}
}
