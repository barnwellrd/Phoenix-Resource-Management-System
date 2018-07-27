package dao;

public class Resources {
	private int resourceId;
	private String resourceName;
	private String resourceDescription;
	private String resourceRoomNumber;
	private int resourceTypeId;
	private int locationId;
	private int isAvailable;
	
	
	
	
	
	@Override
	public String toString() {
		return "Resources: resourceId=" + resourceId + ", resourceName=" + resourceName + ", resourceDescription="
				+ resourceDescription + ", resourceRoomNumber=" + resourceRoomNumber + ", resourceTypeId="
				+ resourceTypeId + ", locationId=" + locationId + ", isAvailable=" + isAvailable;
	}
	
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceDescription() {
		return resourceDescription;
	}
	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}
	public String getResourceRoomNumber() {
		return resourceRoomNumber;
	}
	public void setResourceRoomNumber(String resourceRoomNumber) {
		this.resourceRoomNumber = resourceRoomNumber;
	}
	public int getResourceTypeId() {
		return resourceTypeId;
	}
	public void setResourceTypeId(int resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
}
