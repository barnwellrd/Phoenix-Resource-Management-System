package rms.queries;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

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
		return jtemp.queryForObject("SELECT * FROM USERS WHERE USER_EMAIL = ? AND USER_PASSWORD = ?", 
				new UsersMapper(), email, password);
	}
	
	public Users loginOnUserName(String userName, String password) throws EmptyResultDataAccessException {
		return jtemp.queryForObject("SELECT * FROM USERS WHERE user_name = ? AND user_password = ?", 
				new UsersMapper(), userName, password);
	}

	
}
