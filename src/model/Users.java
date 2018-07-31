package dao.users;

public class Users {
	private int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private int userType;
	private String userPhone;
	private int locationId;
	
	
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + ", userType=" + userType + ", userPhone=" + userPhone + ", locationId=" + locationId
				+ "]";
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	
}
