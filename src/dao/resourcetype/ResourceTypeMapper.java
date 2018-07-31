package dao.resourcetype;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class ResourceTypeMapper implements RowMapper<ResourceType>{

	@Override
	public ResourceType mapRow(ResultSet result, int arg1) throws SQLException {

		ResourceType resType = new ResourceType();
		resType.setResourceTypeId(result.getInt(1));
		resType.setResourceTypeName(result.getString(2));
		resType.setResourceTypeDescription(result.getString(3));
		resType.setImgPath(result.getString(4));
		resType.setIsForSuperuser(result.getInt(5));
		return resType;
	}

}
