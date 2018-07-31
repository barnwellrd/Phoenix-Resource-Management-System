package mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Visitors;

public class VisitorsMapper implements RowMapper<Visitors>{

	@Override
	public Visitors mapRow(ResultSet result, int arg1) throws SQLException {
		Visitors visitor = new Visitors();
		visitor.setVisitorId(result.getInt(1));
		visitor.setName(result.getString(2));
		visitor.setVisitingName(result.getString(3));
		visitor.setEmail(result.getString(4));
		visitor.setPhone(result.getString(5));
		visitor.setInTime(result.getTimestamp(6));
		visitor.setLocationId(result.getInt(7));
		visitor.setBadgeId(result.getString(8));
		visitor.setOutTime(result.getTimestamp(9));
		visitor.setBookingId(result.getInt(10));
		visitor.setVisitPurpose(result.getString(11));
		visitor.setCompanyName(result.getString(12));
		visitor.setIdProof(result.getString(13));
		visitor.setCheckedOut(result.getTimestamp(14));
		return visitor;
	}

}
