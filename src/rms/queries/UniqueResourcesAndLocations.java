package rms.queries;
import rms.model.Resources;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.ResourcesMapper;


public class UniqueResourcesAndLocations {
	ApplicationContext context;
	JdbcTemplate jtemp;
	
	public UniqueResourcesAndLocations() {
        this.context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.jtemp = (JdbcTemplate)context.getBean("jt");
	}
	
	public List<Resources> getResourcesByLocation(int locationId){
		return jtemp.query("SELECT * FROM Resources WHERE location_id = ?", new ResourcesMapper(), locationId);
	}
	
	public List<Resources> getResourcesByLocationAndResourceType(int locationId, int resourceTypeId){
		return jtemp.query("SELECT * FROM Resources "
										+ "WHERE location_id = ? "
										+ "AND resource_type_id = ?", 
										new ResourcesMapper(),
										locationId,
										resourceTypeId);
	}
	
	
	public List<String> getDistinctResourceIdAndName(){
		return jtemp.queryForList("SELECT DISTINCT resource_type_id||resource_name FROM resources", String.class);
	}
	
	public List<String> getLocationAndCity(){
		return jtemp.queryForList("SELECT location_id||city FROM locations", String.class);
	}
	
	public List<Integer> getMostRecentResourceId() {
		return jtemp.queryForList("select max(resource_id) from resources", Integer.class);
	}
	
	public List<String> ResourceTypeName(int resourceTypeId) {
		return jtemp.queryForList("SELECT resource_type_name FROM resource_type WHERE resource_type_id=?", String.class, resourceTypeId );
	}
}
