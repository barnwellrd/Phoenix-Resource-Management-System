package rms.model;

public class Guests 
{
	private int bookingId;
	private int userId;
	private int visitorId;
	
	@Override
	public String toString() 
	{
		return "Guests [bookingId=" + bookingId + ", userId=" + userId + ", visitorId=" + visitorId + "]";
	}
	
	public int getBookingId() 
	{
		return bookingId;
	}
	public void setBookingId(int bookingId) 
	{
		this.bookingId = bookingId;
	}
	
	
	public int getUserId() 
	{
		return userId;
	}
	public void setUserId(int userId) 
	{
		this.userId = userId;
	}
	
	
	public int getVisitorId() 
	{
		return visitorId;
	}
	public void setVisitorId(int visitorId) 
	{
		this.visitorId = visitorId;
	}
	
	
}
