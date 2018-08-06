package rms.model;

import java.sql.Timestamp;

public class Visitors {
	private int visitorId;
	private String visitingName;
	private String email;
	private String phone;
	private Timestamp checkedInTime;
	private int locationId;
	private String badgeId;
	private Timestamp checkedOutTime;
	private String visitPurpose;
	private String companyName;
	private int hasCheckedOut;
	private String firstName;
	private String lastName;
	private String scheduledMeetingTime;
	private int hasIdProof;
	
	
	
	
	
	
	
	public Visitors() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Visitors(int visitorId, String visitingName, String email, String phone, Timestamp checkedInTime,
			int locationId, String badgeId, Timestamp checkedOutTime, String visitPurpose, String companyName,
			int hasCheckedOut, String firstName, String lastName, String scheduledMeetingTime, int hasIdProof) {
		super();
		this.visitorId = visitorId;
		this.visitingName = visitingName;
		this.email = email;
		this.phone = phone;
		this.checkedInTime = checkedInTime;
		this.locationId = locationId;
		this.badgeId = badgeId;
		this.checkedOutTime = checkedOutTime;
		this.visitPurpose = visitPurpose;
		this.companyName = companyName;
		this.hasCheckedOut = hasCheckedOut;
		this.firstName = firstName;
		this.lastName = lastName;
		this.scheduledMeetingTime = scheduledMeetingTime;
		this.hasIdProof = hasIdProof;
	}
	@Override
	public String toString() {
		return "Visitors [visitorId=" + visitorId + ", visitingName=" + visitingName + ", email=" + email + ", phone="
				+ phone + ", checkedInTime=" + checkedInTime + ", locationId=" + locationId + ", badgeId=" + badgeId
				+ ", checkedOutTime=" + checkedOutTime + ", visitPurpose=" + visitPurpose + ", companyName="
				+ companyName + ", hasCheckedOut=" + hasCheckedOut + ", firstName=" + firstName + ", lastName="
				+ lastName + ", scheduledMeetingTime=" + scheduledMeetingTime + ", hasIdProof=" + hasIdProof + "]";
	}
	public int getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
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
	public Timestamp getCheckedInTime() {
		return checkedInTime;
	}
	public void setCheckedInTime(Timestamp checkedInTime) {
		this.checkedInTime = checkedInTime;
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
	public Timestamp getCheckedOutTime() {
		return checkedOutTime;
	}
	public void setCheckedOutTime(Timestamp checkedOutTime) {
		this.checkedOutTime = checkedOutTime;
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
	public int getHasCheckedOut() {
		return hasCheckedOut;
	}
	public void setHasCheckedOut(int hasCheckedOut) {
		this.hasCheckedOut = hasCheckedOut;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getScheduledMeetingTime() {
		return scheduledMeetingTime;
	}
	public void setScheduledMeetingTime(String scheduledMeetingTime) {
		this.scheduledMeetingTime = scheduledMeetingTime;
	}
	public int getHasIdProof() {
		return hasIdProof;
	}
	public void setHasIdProof(int hasIdProof) {
		this.hasIdProof = hasIdProof;
	}
	
	
	
	

	
	
}
