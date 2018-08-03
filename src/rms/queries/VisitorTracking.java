package rms.queries;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

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
	
}
