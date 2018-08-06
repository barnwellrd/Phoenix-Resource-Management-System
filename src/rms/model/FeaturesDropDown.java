package rms.model;

public class FeaturesDropDown
{
	private String featureName;
	private int quantity;
	private String resourceName;
	
	@Override
	public String toString() 
	{
		return "Features [featureName=" + featureName + ", quantity=" + quantity + ", resourceName=" + resourceName
				+ "]";
	}
	
	
	public String getFeatureName() 
	{
		return featureName;
	}
	public void setFeatureName(String featureTypeId) 
	{
		this.featureName = featureTypeId;
	}
	
	
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	
	public String getResourceName()
	{
		return resourceName;
	}


	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	
}