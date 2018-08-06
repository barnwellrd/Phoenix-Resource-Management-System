package rms.queries;

import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import rms.mapper.RoomDropMapper;
import rms.model.FeaturesDropDown;


/**
 * Class containing queries involving the features table
 * @author syntel
 *
 */
public class FeatureQueries {
	
	private ApplicationContext context;
	private JdbcTemplate jtemp;
	
	
	/**
	 * Constructor for the FeatureQueries class. (No args required)
	 */
	public FeatureQueries() {
        this.context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.jtemp = (JdbcTemplate)context.getBean("jt");
	}
	
	public List<FeaturesDropDown> getFeatureNameAndQuantityByResouceId() {
		return jtemp.query("SELECT FT.Feature_Type_Name, F.Quantity, R.Resource_Name FROM FEATURES F JOIN FEATURE_TYPE FT ON F.Feature_Type_ID = FT.Feature_Type_ID JOIN RESOURCES R ON F.Resource_ID = R.Resource_ID",
				new RoomDropMapper());
	}	
	
}