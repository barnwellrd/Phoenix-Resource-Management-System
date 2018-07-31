package rms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import rms.model.Features;

public class FeaturesMapper implements RowMapper<Features>
{
	@Override
	public Features mapRow(ResultSet result, int arg1) throws SQLException 
	{
		Features feat = new Features();
		feat.setFeatureTypeId(result.getInt(1));
		feat.setQuantity(result.getInt(2));
		feat.setResourceId(result.getInt(3));
		
		return feat;
	}
}
