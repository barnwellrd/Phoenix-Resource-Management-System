package rms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rms.model.FeaturesDropDown;
import rms.model.Features;

public class RoomDropMapper implements RowMapper<FeaturesDropDown>
{
	@Override
	public FeaturesDropDown mapRow(ResultSet result, int arg1) throws SQLException 
	{
		FeaturesDropDown feat = new FeaturesDropDown();
		feat.setFeatureName(result.getString(1));
		feat.setQuantity(result.getInt(2));
		feat.setResourceName(result.getString(3));
		
		return feat;
	}
}
