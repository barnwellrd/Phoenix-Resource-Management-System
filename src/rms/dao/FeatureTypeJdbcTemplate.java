package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.FeatureTypeMapper;
import rms.model.FeatureType;

public class FeatureTypeJdbcTemplate implements JdbcTemplateInterface<FeatureType>
{
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public FeatureTypeJdbcTemplate() 
	{
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
	@Override
	public int insert(FeatureType ftype) 
	{
		int result = jtemp.update("INSERT INTO Feature_Type VALUES(seq_feature_type.nextval, ?, ?, ?)",
		ftype.getFeatureTypeName(),
		ftype.getFeatureTypeDescription(),
		ftype.getImgPath());

		return result;
	}
	
	@Override
	public int delete(int featureTypeId) 
	{
		int result = jtemp.update("DELETE FROM Feature_Type WHERE Feature_Type_Id = ?", featureTypeId);
		return result;
	}
	
	@Override
	public int update(FeatureType ftype)
	{
		int result = jtemp.update("UPDATE Feature_Type "
				+ "SET feature_type_name = ?, "
				+ "feature_type_description = ?, "
				+ "img_path = ? "
				+ "WHERE feature_type_id = ?",
				ftype.getFeatureTypeName(),
				ftype.getFeatureTypeDescription(),
				ftype.getImgPath(),
				ftype.getFeatureTypeId());

		return result;
	}
	
	@Override
	public FeatureType search(int featureTypeId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException
	{
		FeatureType ft = jtemp.queryForObject("SELECT * FROM Feature_Type WHERE feature_type_id = ? ",
				new FeatureTypeMapper(), featureTypeId);
		return ft;
	}
	
	@Override
	public List<FeatureType> getAll() 
	{
		List<FeatureType> featureTypeList = jtemp.query("SELECT * FROM Feature_Type", new FeatureTypeMapper());
		
		return featureTypeList;
	}
	

}
