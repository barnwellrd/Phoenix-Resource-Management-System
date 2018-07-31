package dao.resourcetype;

public class ResourceType {
	int resourceTypeId;
	String resourceTypeName;
	String resourceTypeDescription;
	String imgPath;
	int isForSuperuser;
	
	

	
	@Override
	public String toString() {
		return "ResourceType: resourceTypeId=" + resourceTypeId + ", resourceTypeName=" + resourceTypeName
				+ ", resourceTypeDescription=" + resourceTypeDescription + ", imgPath=" + imgPath + ", isForSuperuser="
				+ isForSuperuser;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public int getIsForSuperuser() {
		return isForSuperuser;
	}
	public void setIsForSuperuser(int isForSuperuser) {
		this.isForSuperuser = isForSuperuser;
	}
	public int getResourceTypeId() {
		return resourceTypeId;
	}
	public void setResourceTypeId(int resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}
	public String getResourceTypeName() {
		return resourceTypeName;
	}
	public void setResourceTypeName(String resourceTypeName) {
		this.resourceTypeName = resourceTypeName;
	}
	public String getResourceTypeDescription() {
		return resourceTypeDescription;
	}
	public void setResourceTypeDescription(String resourceTypeDescription) {
		this.resourceTypeDescription = resourceTypeDescription;
	}
	
}
