package rms.model;

import java.sql.Timestamp;

public class Visitors {
	private int visitorId;
	private String name;
	private String visitingName;
	private String email;
	private String phone;
	private Timestamp inTime;
	private int locationId;
	private String badgeId;
	private Timestamp outTime;
	private int bookingId;
	private String visitPurpose;
	private String companyName;
	private String idProof;
	private Timestamp checkedOut;
	
	
	
	
	@Override
	public String toString() {
		return "Visitors [visitorId=" + visitorId + ", name=" + name + ", visitingName=" + visitingName + ", email="
				+ email + ", phone=" + phone + ", inTime=" + inTime + ", locationId=" + locationId + ", badgeId="
				+ badgeId + ", outTime=" + outTime + ", bookingId=" + bookingId + ", visitPurpose=" + visitPurpose
				+ ", companyName=" + companyName + ", idProof=" + idProof + ", checkedOut=" + checkedOut + "]";
	}
	
	public int getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVisitingName() {
		return visitingName;
	}
	public void setVisitingName(String visitingName) {
		this.visitingName = visitingName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getInTime() {
		return inTime;
	}
	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getBadgeId() {
		return badgeId;
	}
	public void setBadgeId(String badgeId) {
		this.badgeId = badgeId;
	}
	public Timestamp getOutTime() {
		return outTime;
	}
	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getVisitPurpose() {
		return visitPurpose;
	}
	public void setVisitPurpose(String visitPurpose) {
		this.visitPurpose = visitPurpose;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	public Timestamp getCheckedOut() {
		return checkedOut;
	}
	public void setCheckedOut(Timestamp checkedOut) {
		this.checkedOut = checkedOut;
	}
	
	
}
