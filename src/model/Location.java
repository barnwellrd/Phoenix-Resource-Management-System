package model;

public class Location {
	
	private int locationId;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String phone;
	
	public Location() {
		super();
	}

	public Location(String city, String state, String zip, String country, String phone) {
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.phone = phone;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
