package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.model.Features;
import rms.mapper.FeaturesMapper;

/*     -------WARNING-------
 Does not implement JdbcTemplateInterface
 Has unique methods
 Please read comments on methods below
 for more information
*/

public class FeaturesJdbcTemplate
{

	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public FeaturesJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}

	public int insert(Features feat)
	{
		int result = jtemp.update("INSERT INTO Features VALUES(?, ?, ?)",
		feat.getFeatureTypeId(),
		feat.getQuantity(),
		feat.getResourceId());

		return result;
	}
	
	// Accepts 2 arguments rather than one:
	// Table's primary key is based on two values
	// Delete by BOTH Feature_Type_Id AND Resource_Id
	public int deleteByFeatureTypeIdAndResourceId(int ftypeId, int resourceId) 
	{
		int result = jtemp.update("DELETE FROM Features WHERE Feature_Type_Id= ? "
				+ "AND Resource_Id = ?", ftypeId, resourceId);
		return result;
	}

	// Delete by Resource_Id
	public int deleteByResourceId(int resourceId) 
	{
		int result = jtemp.update("DELETE FROM Features WHERE Resource_Id = ?", resourceId);
		return result;
	}

	// Must input both Feature_Type_Id
	// AND Resource_Id to update Features table
	public int update(Features feat) 
	{
		int result = jtemp.update("UPDATE Features "
				+ "SET quantity = ? "
				+ "WHERE feature_type_id = ?, "
				+ "AND resource_id = ?",
				feat.getQuantity(),
				feat.getFeatureTypeId(),
				feat.getResourceId());

		return result;
	}

	// Accepts 2 arguments rather than one:
	// Table's primary key is based on two values
	// Searching by BOTH Feature_Type_Id AND Resource_Id
	public Features searchByFeatureTypeIdAndResourceId(int ftypeId, int resourceId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException
	{
		Features feat = jtemp.queryForObject("SELECT * FROM Features "
				+ "WHERE feature_type_id = ? AND resource_id = ?",
				new FeaturesMapper(), ftypeId, resourceId);
		return feat;
	}
	
	// Search by Feature_Type_Id
	public List<Features> searchByFeatureTypeId(int ftypeId)
	{
		List<Features> feats = jtemp.query("SELECT * FROM Features WHERE feature_type_id = ?",new FeaturesMapper(), ftypeId);
		return feats;
	}
	
	// Search by Resource_Id
	public List<Features> searchByResourceId(int resourceId) 
	{
		List<Features> feats = jtemp.query("SELECT * FROM Features WHERE resource_id = ?",new FeaturesMapper(), resourceId);
		return feats;
	}

	public List<Features> getAll() 
	{
		List<Features> featuresList = jtemp.query("SELECT * FROM Features", new FeaturesMapper());
		
		return featuresList;
	}
	
	
}
