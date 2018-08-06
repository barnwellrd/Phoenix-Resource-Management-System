package rms.queries;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;

import rms.mapper.UsersMapper;
import rms.model.Users;

public class LoginQueries {
	private ApplicationContext context;
	private JdbcTemplate jtemp;
	
	public LoginQueries() {

        this.context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.jtemp = (JdbcTemplate)context.getBean("jt");
	}
	
	public Users loginOnEmail(String email, String password) throws EmptyResultDataAccessException {
		Users user = jtemp.queryForObject("SELECT * FROM USERS WHERE USER_EMAIL = ?", new UsersMapper(), email);
		String pswdHash = user.getUserPassword();
		if (BCrypt.checkpw(password, pswdHash)) {
			return user;
		} else {
			throw new EmptyResultDataAccessException(1);
		}
	}
	
	public Users loginOnUserName(String userName, String password) throws EmptyResultDataAccessException {
		Users user = jtemp.queryForObject("SELECT * FROM USERS WHERE user_name = ?", new UsersMapper(), userName);
		String pswdHash = user.getUserPassword();
		if (BCrypt.checkpw(password, pswdHash)) {
			return user;
		} else {
			throw new EmptyResultDataAccessException(1);
		}
	}
	
	public boolean checkIsAdminUsingEmail(String email, String password) throws EmptyResultDataAccessException {
		int userType = jtemp.queryForObject("SELECT user_type FROM users WHERE user_email = ? AND user_password = ?", 
				int.class, email, password);
		if(userType == 1 || userType == 3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkIsAdminUsingUsername(String userName, String password) throws EmptyResultDataAccessException {
		int userType = jtemp.queryForObject("SELECT user_type FROM users WHERE user_name = ? AND user_password = ?", 
				int.class, userName, password);
		if(userType == 1 || userType == 3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
