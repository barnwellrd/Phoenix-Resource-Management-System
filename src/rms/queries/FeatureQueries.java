package rms.queries;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class FeatureQueries {
	
	private ApplicationContext context;
	private JdbcTemplate jtemp;
	
	public FeatureQueries() {
        this.context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.jtemp = (JdbcTemplate)context.getBean("jt");
	}
	
	public List<Map<String, Object>> getFeatureNameAndQuantityByResouceId(int resourceId) {
		return jtemp.queryForList("SELECT feature_type_name, quantity, resource_id FROM features JOIN feature_type USING(feature_type_id) WHERE resource_id=? ORDER BY feature_type_name ASC",
				resourceId);
	}
}