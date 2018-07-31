package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.VisitorsMapper;
import rms.model.Visitors;


public class VisitorsJdbcTemplate implements JdbcTemplateInterface<Visitors>{
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public VisitorsJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
	@Override
	public int insert(Visitors visitorToInsert) {
		String statement = "INSERT INTO VISITORS VALUES(seq_visitors.nextval, "
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = jtemp.update(statement, visitorToInsert.getName(),
								visitorToInsert.getVisitingName(),
								visitorToInsert.getEmail(),
								visitorToInsert.getPhone(),
								visitorToInsert.getInTime(),
								visitorToInsert.getLocationId(),
								visitorToInsert.getBadgeId(),
								visitorToInsert.getOutTime(),
								visitorToInsert.getBookingId(),
								visitorToInsert.getVisitPurpose(),
								visitorToInsert.getCompanyName(),
								visitorToInsert.getIdProof(),
								visitorToInsert.getCheckedOut());
		return result;
	}

	@Override
	public int delete(int visitorIdToDelete) {
		
		return jtemp.update("DELETE FROM visitors where visitor_id = ?", visitorIdToDelete);
	}

	@Override
	public int update(Visitors visitorToUpdate) {
		String statement = "UPDATE visitors SET "
							+ "name = ?,"
							+ "visiting_name = ?,"
							+ "email = ?,"
							+ "phone = ?,"
							+ "in_time = ?,"
							+ "location_id = ?,"
							+ "badge_id = ?,"
							+ "out_time = ?,"
							+ "booking_id = ?,"
							+ "visit_purpose = ?,"
							+ "company_name = ?,"
							+ "id_proof = ?,"
							+ "checked_out = ? "
							+ "WHERE visitor_id = ?";
		
		int result = jtemp.update(statement, 
				visitorToUpdate.getName(),
				visitorToUpdate.getVisitingName(),
				visitorToUpdate.getEmail(),
				visitorToUpdate.getPhone(),
				visitorToUpdate.getInTime(),
				visitorToUpdate.getLocationId(),
				visitorToUpdate.getBadgeId(),
				visitorToUpdate.getOutTime(),
				visitorToUpdate.getBookingId(),
				visitorToUpdate.getVisitPurpose(),
				visitorToUpdate.getCompanyName(),
				visitorToUpdate.getIdProof(),
				visitorToUpdate.getCheckedOut(),
				visitorToUpdate.getVisitorId());
		
		
		return result;
	}

	@Override
	public Visitors search(int visitorId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException{		
		
		return jtemp.queryForObject("SELECT * FROM visitors WHERE visitor_id = ?", new VisitorsMapper(), visitorId);
	}

	@Override
	public List<Visitors> getAll() {

		return jtemp.query("SELECT * FROM visitors", new VisitorsMapper());
	}

}
