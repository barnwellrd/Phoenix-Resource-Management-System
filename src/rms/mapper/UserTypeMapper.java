package rms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rms.model.UserType;

public class UserTypeMapper implements RowMapper<UserType>{

	@Override
	public UserType mapRow(ResultSet result, int arg1) throws SQLException {
		UserType ut = new UserType();
		ut.setUserTypeId(result.getInt(1));
		ut.setUserTypeName(result.getString(2));
		ut.setUserTypeDescription(result.getString(3));
		return ut;
	}

}
