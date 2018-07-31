package dao.resourcetype;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import dao.JdbcTemplateInterface;

public class ResourceTypeJdbcTemplate implements JdbcTemplateInterface<ResourceType>{
	
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public ResourceTypeJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}

	@Override
	public int insert(ResourceType resourceTypeToInsert) {
		
		return jtemp.update("insert into Resource_Type "
				+ "values(seq_resource_type.nextval,?,?,?,?)", 
				resourceTypeToInsert.getResourceTypeName(), 
				resourceTypeToInsert.getResourceTypeDescription(),
				resourceTypeToInsert.getImgPath(),
				resourceTypeToInsert.getIsForSuperuser());
	}

	@Override
	public int delete(int resourceTypeIdToDelete) {
		
		return jtemp.update("delete from Resource_Type where resource_type_id = ?", resourceTypeIdToDelete);
	}

	@Override
	public int update(ResourceType resourceTypeToUpdate) {

		return jtemp.update("update Resource_Type "
							+ "set "
							+ "resource_type_name = ?, "
							+ "resource_type_description = ?, "
							+ "img_path = ?, "
							+ "is_for_superuser = ? "
							+ "where "
							+ "resource_type_id = ?",
							resourceTypeToUpdate.getResourceTypeName(),
							resourceTypeToUpdate.getResourceTypeDescription(),
							resourceTypeToUpdate.getImgPath(),
							resourceTypeToUpdate.getIsForSuperuser(),
							resourceTypeToUpdate.getResourceTypeId());
	}

	@Override
	public ResourceType search(int resourceTypeId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		
		return jtemp.queryForObject("SELECT * FROM Resource_Type WHERE resource_type_id = ?", new ResourceTypeMapper(), resourceTypeId);
	}

	@Override
	public List<ResourceType> getAll() {

		return jtemp.query("SELECT * FROM resource_type", new ResourceTypeMapper());
	}

}
