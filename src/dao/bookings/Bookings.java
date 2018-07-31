package dao.bookings;

import java.sql.Timestamp;

public class Bookings {
	private int bookingId;
	private int resourceId;
	private int userId;
	private int isActive;
	private Timestamp bookedStartTime;
	private Timestamp bookedEndTime;
	
	
	
	
	@Override
	public String toString() {
		return "Bookings: bookingId=" + bookingId + ", resourceId=" + resourceId + ", userId=" + userId + ", isActive="
				+ isActive + ", bookedStartTime=" + bookedStartTime + ", bookedEndTime=" + bookedEndTime;
	}
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public Timestamp getBookedStartTime() {
		return bookedStartTime;
	}
	public void setBookedStartTime(Timestamp bookedStartTime) {
		this.bookedStartTime = bookedStartTime;
	}
	public Timestamp getBookedEndTime() {
		return bookedEndTime;
	}
	public void setBookedEndTime(Timestamp bookedEndTime) {
		this.bookedEndTime = bookedEndTime;
	}
}
