package dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;


public class ResourcesJdbcTemplate {
    JdbcTemplate jtemp;

    
    
	public JdbcTemplate getJtemp() {
		return jtemp;
	}

	public void setJtemp(JdbcTemplate jtemp) {
		this.jtemp = jtemp;
	}
	
	public int insertResource(Resources resource){
		int result = jtemp.update("insert into Resources values(seq_resource.nextval, ?, ?, ?, ?, ?, ?)", 
														resource.getResourceName(),
														resource.getResourceDescription(),
														resource.getResourceRoomNumber(),
														resource.getResourceTypeId(),
														resource.getLocationId(),
														resource.getIsAvailable()
														);
		return result;
	}
	
	public int deleteResource(int resourceId){
		int result = jtemp.update("delete from Resources where resource_id = ?", resourceId);
		
		return result;
	}
	
	public int updateResource(Resources resource){
		int result = jtemp.update("update Resources "
									+ "set resource_name = ?, "
									+ "resource_description = ?, "
									+ "resource_room_number = ?, "
									+ "resource_type_id = ?, "
									+ "location_id = ?, "
									+ "is_available = ? "
									+ "where resource_id = ?", 
									resource.getResourceName(),
									resource.getResourceDescription(),
									resource.getResourceRoomNumber(),
									resource.getResourceTypeId(),
									resource.getLocationId(),
									resource.getIsAvailable(),
									resource.getResourceId());
		
		return result;
	}
	
	public Resources searchResource(int resourceId){
		Resources rs = jtemp.queryForObject("SELECT * FROM Resources WHERE resource_id = ? ", new ResourcesMapper(), resourceId);
		
		return rs;
	}
	
	
	public List<Resources> getAllResources(){
		
		List<Resources> resourcesList = jtemp.query("SELECT * FROM Resources", new ResourcesMapper());
		
		return resourcesList;
	}

    
}
