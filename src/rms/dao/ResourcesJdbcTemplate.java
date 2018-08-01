package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;


import rms.model.Resources;
import rms.mapper.ResourcesMapper;

public class ResourcesJdbcTemplate implements JdbcTemplateInterface<Resources>{
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public ResourcesJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
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
	
	@Override
	public int delete(int resourceId){
		int result = jtemp.update("delete from Resources where resource_id = ?", resourceId);
		
		return result;
	}
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
	@Override
	public Resources search(int resourceId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException{
		Resources rs = jtemp.queryForObject("SELECT * FROM Resources WHERE resource_id = ? ", new ResourcesMapper(), resourceId);
		
		return rs;
	}
	
	@Override
	public List<Resources> getAll(){
		
		List<Resources> resourcesList = jtemp.query("SELECT * FROM Resources", new ResourcesMapper());
		
		return resourcesList;
	}

    
}
