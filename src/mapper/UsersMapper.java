package dao.users;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UsersMapper implements RowMapper<Users>{

	@Override
	public Users mapRow(ResultSet result, int arg1) throws SQLException {
		Users user = new Users();
		user.setUserId(result.getInt(1));
		user.setUserName(result.getString(2));
		user.setUserEmail(result.getString(3));
		user.setUserPassword(result.getString(4));
		user.setUserType(result.getInt(5));
		user.setUserPhone(result.getString(6));
		user.setLocationId(result.getInt(7));
		return user;
	}

}
