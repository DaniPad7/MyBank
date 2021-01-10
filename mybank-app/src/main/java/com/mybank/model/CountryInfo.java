package com.mybank.model;

public class CountryInfo {
	private int countryId;
	private String homeCountry;
	
	public CountryInfo() {
		super();
	}
	
	public CountryInfo(int countryId, String homeCountry) {
		super();
		this.countryId = countryId;
		this.homeCountry = homeCountry;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getHomeCountry() {
		return homeCountry;
	}
	public void setHomeCountry(String homeCountry) {
		this.homeCountry = homeCountry;
	}
	
	@Override
	public String toString() {
		return "CountryInfo [countryId=" + countryId + ", homeCountry=" + homeCountry + "]";
	}
	
	

}
