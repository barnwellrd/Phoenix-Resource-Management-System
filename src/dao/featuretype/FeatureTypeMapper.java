package dao.featuretype;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FeatureTypeMapper implements RowMapper<FeatureType>
{
	@Override
	public FeatureType mapRow(ResultSet result, int arg1) throws SQLException 
	{
		FeatureType ftype = new FeatureType();
		ftype.setFeatureTypeId(result.getInt(1));
		ftype.setFeatureTypeName(result.getString(2));
		ftype.setFeatureTypeDescription(result.getString(3));
		ftype.setImgPath(result.getString(4));
		
		return ftype;
	}
}
