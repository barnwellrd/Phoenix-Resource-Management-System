package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import model.Resources;

public class ResourcesMapper implements RowMapper<Resources>{


		@Override
		public Resources mapRow(ResultSet result, int ar1) throws SQLException {
			Resources res = new Resources();
			res.setResourceId(result.getInt(1));
			res.setResourceName(result.getString(2));
			res.setResourceDescription(result.getString(3));
			res.setResourceRoomNumber(result.getString(4));
			res.setResourceTypeId(result.getInt(5));
			res.setLocationId(result.getInt(6));
			res.setIsAvailable(result.getInt(7));
			
			return res;
		}

}
