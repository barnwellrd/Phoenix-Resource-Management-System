package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.LocationMapper;
import rms.model.Location;


public class LocationJdbcTemplate implements JdbcTemplateInterface<Location>{
    
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public LocationJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}

	@Override
	public int insert(Location location) {
		return jtemp.update("INSERT INTO locations VALUES (seq_location.nextval, ?, ?, ?, ?, ?)",
				location.getCity(),
				location.getState(),
				location.getZip(),
				location.getCountry(),
				location.getPhone());
	}

	@Override
	public int delete(int id) {
		return jtemp.update("DELETE FROM locations WHERE location_id=?", id);
	}

	@Override
	public int update(Location location) {
		return jtemp.update(
				"UPDATE locations SET city=?, state=?, zip_code=?, country=?, phone=? WHERE location_id=?",
				location.getCity(),
				location.getState(),
				location.getZip(),
				location.getCountry(),
				location.getPhone(),
				location.getLocationId());
	}

	@Override
	public Location search(int id) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		return jtemp.queryForObject(
					"SELECT * FROM locations WHERE location_id=?", 
					new LocationMapper(), id);
	}

	@Override
	public List<Location> getAll() {
		return jtemp.query("SELECT * FROM locations", new LocationMapper());
	}
    
}
