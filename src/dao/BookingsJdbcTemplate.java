package dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import mapper.BookingsMapper;
import model.Bookings;

public class BookingsJdbcTemplate implements JdbcTemplateInterface<Bookings>{
	
	JdbcTemplate jtemp;
	ApplicationContext context;
	
	public BookingsJdbcTemplate() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
		jtemp = (JdbcTemplate) context.getBean("jt");
	}

	@Override
	public int insert(Bookings bookings){
		int result = jtemp.update("insert into Bookings values(seq_booking.nextval, ?, ?, ?, ?, ?)", 
				bookings.getResourceId(),
				bookings.getUserId(),
				bookings.getIsActive(),
				bookings.getBookedStartTime(),
				bookings.getBookedEndTime());
														
		return result;
	}
	
	@Override
	public int delete(int bookingId){
		int result = jtemp.update("delete from Bookings where booking_id = ?", bookingId);
		
		return result;
	}
	@Override
	public int update(Bookings booking){
		int result = jtemp.update("update Bookings set "
										+ "resource_id = ?, "
										+ "user_id = ?, "
										+ "is_active = ?, "
										+ "booked_start_time = ?, "
										+ "booked_end_time = ? "
										+ "WHERE booking_id = ?"
									,
									booking.getResourceId(),
									booking.getUserId(),
									booking.getIsActive(),
									booking.getBookedStartTime(),
									booking.getBookedEndTime(),
									booking.getBookingId()
									);
		
		return result;
	}
	@Override
	public Bookings search(int bookingId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException{
		Bookings bk = jtemp.queryForObject("SELECT * FROM Bookings WHERE booking_id = ? ", new BookingsMapper(), bookingId);
		
		return bk;
	}
	
	@Override
	public List<Bookings> getAll(){
		
		List<Bookings> BookingList = jtemp.query("SELECT * FROM Bookings", new BookingsMapper());
		
		return BookingList;
	}


}
