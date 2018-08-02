package rms.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import rms.mapper.GuestsMapper;
import rms.model.Guests;

public class GuestsJdbcTemplate
{
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public GuestsJdbcTemplate() 
	{
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}
	
	public int insert(Guests guest)
	{
		int result = jtemp.update("INSERT INTO Guests VALUES(?, ?, ?)", 
				guest.getBookingId(),
				guest.getUserId(),
				guest.getVisitorId());
														
		return result;
	}

	public int delete(int bookingId) 
	{
		int result = jtemp.update("DELETE FROM Guests WHERE booking_id = ?", bookingId);
		
		return result;
	}

	public int update(Guests guest) 
	{
		int result = jtemp.update("UPDATE Guests SET"
				+ " user_id = ?,"
				+ " visitor_id = ?"
				+ " WHERE booking_id = ? AND user_id = ?",				
			guest.getUserId(),
			guest.getVisitorId(),
			guest.getBookingId());
		
		return result;
	}

	public List<Guests> search(int bookingId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException
	{
		return jtemp.query("SELECT * FROM Guests WHERE booking_id = ? ", new GuestsMapper(), bookingId);
	}

	public List<Guests> getAll()
	{
		List<Guests> guestList = jtemp.query("SELECT * FROM Guests", new GuestsMapper());
		
		return guestList;
	}

}
