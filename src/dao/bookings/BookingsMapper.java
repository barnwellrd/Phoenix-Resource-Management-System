package dao.bookings;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookingsMapper implements RowMapper<Bookings>{

	
	public Bookings mapRow(ResultSet result, int ar1) throws SQLException {
		Bookings bkn = new Bookings();
		bkn.setBookingId(result.getInt(1));
		bkn.setResourceId(result.getInt(2));
		bkn.setUserId(result.getInt(3));
		bkn.setIsActive(result.getInt(4));
		bkn.setBookedStartTime(result.getTimestamp(5));
		bkn.setBookedEndTime(result.getTimestamp(6));
		return bkn;
	}

}
