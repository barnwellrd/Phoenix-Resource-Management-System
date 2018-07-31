package dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import dao.JdbcTemplateInterface;
import model.Users;
import mapper.UsersMapper;

public class UsersJdbcTemplate implements JdbcTemplateInterface<Users>{
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public UsersJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
	@Override
	public int insert(Users userToInsert) {
		int result = jtemp.update("INSERT INTO Users VALUES(seq_user.nextVal, ?, ?, ?, ?, ?, ?)", 
																userToInsert.getUserName(),
																userToInsert.getUserEmail(),
																userToInsert.getUserPassword(),
																userToInsert.getUserType(),
																userToInsert.getUserPhone(),
																userToInsert.getLocationId());
		return result;

	}

	@Override
	public int delete(int userIdToDelete) {
		int result = jtemp.update("DELETE FROM users WHERE user_id = ?", userIdToDelete);
		
		return result;	
	}

	@Override
	public int update(Users userToUpdate) {
		int result = jtemp.update("UPDATE users "
								+ "SET "
									+ "user_name = ?, "
									+ "user_email = ?, "
									+ "user_password = ?, "
									+ "user_type = ?, "
									+ "user_phone = ?, "
									+ "location_id = ? "
								+ "WHERE user_id = ?",
									userToUpdate.getUserName(),
									userToUpdate.getUserEmail(),
									userToUpdate.getUserPassword(),
									userToUpdate.getUserType(),
									userToUpdate.getUserPhone(),
									userToUpdate.getLocationId(),
									userToUpdate.getUserId());
		return result;
	}

	@Override
	public Users search(int userId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		Users user = jtemp.queryForObject("SELECT * FROM Users WHERE user_id = ? ", new UsersMapper(), userId);
		
		return user;
	}

	@Override
	public List<Users> getAll() {
		List<Users> usersList = jtemp.query("SELECT * FROM Users", new UsersMapper());
		return usersList;
	}

}
