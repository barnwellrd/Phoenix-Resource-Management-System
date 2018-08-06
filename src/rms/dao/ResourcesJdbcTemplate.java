package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.model.*;
import rms.mapper.*;

public class ResourcesJdbcTemplate implements JdbcTemplateInterface<Resources>{
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public ResourcesJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#insert(java.lang.Object)
	 */
	@Override
	public int insert(Resources resource){
		int result = jtemp.update("insert into Resources values(seq_resource.nextval, ?, ?, ?, ?, ?, ?, ?, ?)", 
														resource.getResourceName(),
														resource.getResourceDescription(),
														resource.getResourceRoomNumber(),
														resource.getResourceTypeId(),
														resource.getLocationId(),
														resource.getIsAvailable(),
														resource.getIsSuperRoom(),
														resource.getCapacity()
														);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#delete(int)
	 */
	@Override
	public int delete(int resourceId){
		int result = jtemp.update("delete from Resources where resource_id = ?", resourceId);
		
		return result;
	}
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#update(java.lang.Object)
	 */
	@Override
	public int update(Resources resource){
		int result = jtemp.update("update Resources "
									+ "set resource_name = ?, "
									+ "resource_description = ?, "
									+ "resource_room_number = ?, "
									+ "resource_type_id = ?, "
									+ "location_id = ?, "
									+ "is_available = ?, "
									+ "is_super_room = ?, "
									+ "capacity = ? "
									+ "where resource_id = ?", 
									resource.getResourceName(),
									resource.getResourceDescription(),
									resource.getResourceRoomNumber(),
									resource.getResourceTypeId(),
									resource.getLocationId(),
									resource.getIsAvailable(),
									resource.getIsSuperRoom(),
									resource.getCapacity(),
									resource.getResourceId());
		
		return result;
	}
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#search(int)
	 */
	@Override
	public Resources search(int resourceId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException{
		Resources rs = jtemp.queryForObject("SELECT * FROM Resources WHERE resource_id = ? ", new ResourcesMapper(), resourceId);
		
		return rs;
	}
	
	/* (non-Javadoc)
	 * @see rms.dao.JdbcTemplateInterface#getAll()
	 */
	@Override
	public List<Resources> getAll(){
		
		List<Resources> resourcesList = jtemp.query("SELECT * FROM Resources", new ResourcesMapper());
		
		return resourcesList;
	}

	public List<Resources> resourcesByResourceType(int resourceTypeId){
		String sql = "select * from resources where resource_type_id = ?";
		return jtemp.query(sql, new ResourcesMapper(), resourceTypeId);
	}
	
	public List<FeatureType> getFeatures(int resourceId){
		String sql =	"select feature_type.feature_type_id, feature_type.feature_type_name, feature_type.feature_type_description, feature_type.img_path " + 
						"from resources, features, feature_type " +
						"where resources.resource_id = features.resource_id " + 
						"and features.feature_type_id = feature_type.feature_type_id " + 
						"and resources.resource_id = ?";
		return jtemp.query(sql, new FeatureTypeMapper(), resourceId);
	}
}
