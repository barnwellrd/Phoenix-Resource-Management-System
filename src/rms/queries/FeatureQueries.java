package rms.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import rms.model.FeatureNameAndQuantity;

public class FeatureQueries {
	
	private ApplicationContext context;
	private JdbcTemplate jtemp;
	
	public FeatureQueries() {
        this.context = new ClassPathXmlApplicationContext("spring-dao.xml");
        this.jtemp = (JdbcTemplate)context.getBean("jt");
	}
	
	public List<FeatureNameAndQuantity> getFeatureNameAndQuantityByResouceId(int resourceId) {
		
		class FeatureNameAndQuantityMapper implements RowMapper<FeatureNameAndQuantity> {

			@Override
			public FeatureNameAndQuantity mapRow(ResultSet result, int arg1) throws SQLException {
				return new FeatureNameAndQuantity(result.getString(1), result.getInt(2));
			}

		}
		
		return jtemp.query("SELECT feature_type_name, quantity, resource_id FROM features JOIN feature_type USING(feature_type_id) WHERE resource_id=? ORDER BY feature_type_name ASC",
				new FeatureNameAndQuantityMapper(), resourceId);
	}

}