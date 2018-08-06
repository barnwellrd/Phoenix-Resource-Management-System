package rms.queries;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


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
	
	/**
	 * Will query for the feature_type_name and quantity of every feature belonging to a certain resource
	 * @param resourceId The id of the resource
	 * @return A list of maps where each map represents a feature. The map
	 * associates the column names ("feature_type_name" and "quantity" in this case) with the data.
	 */
	public List<Map<String, Object>> getFeatureNameAndQuantityByResouceId(int resourceId) {
		return jtemp.queryForList("SELECT feature_type_name, quantity, resource_id FROM features JOIN feature_type USING(feature_type_id) WHERE resource_id=? ORDER BY feature_type_name ASC",
				resourceId);
	}
}