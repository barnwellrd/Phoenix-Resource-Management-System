package rms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import rms.model.Guests;

public class GuestsMapper implements RowMapper<Guests>
{
	@Override
	public Guests mapRow(ResultSet result, int arg1) throws SQLException 
	{
		Guests guest = new Guests();
		guest.setBookingId(result.getInt(1));
		guest.setUserId(result.getInt(2));
		guest.setVisitorId(result.getInt(3));
		
		return guest;
	}
}
