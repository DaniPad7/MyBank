package com.mybank.model;

import java.sql.Date;

public class UserPersonalInfo {
	private int userId;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String email;
	private String phoneNumber;
	private int countryId;
	private String homeAddress;
	private String homeCity;
	private String homeState;
	private String homeZipCode;
	
	public UserPersonalInfo() {
		super();
	}

	public UserPersonalInfo(/*int userId,*/ String firstName, String lastName, Date birthDate, String email,
			String phoneNumber, int countryId, String homeAddress, String homeCity, String homeState, String homeZipCode) {
		super();
		/*this.userId = userId;*/
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.countryId = countryId;
		this.homeAddress = homeAddress;
		this.homeCity = homeCity;
		this.homeState = homeState;
		this.homeZipCode = homeZipCode;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getHomeState() {
		return homeState;
	}

	public void setHomeState(String homeState) {
		this.homeState = homeState;
	}

	public String getHomeZipCode() {
		return homeZipCode;
	}

	public void setHomeZipCode(String homeZipCode) {
		this.homeZipCode = homeZipCode;
	}

	@Override
	public String toString() {
		return "UserPersonalInfo [ID: " + userId + ", First Name: " + firstName + ", Last Name: " + lastName
				+ ", Birthdate: " + birthDate + ", Email: " + email + ", Phone Number" + phoneNumber + ", Country ID:"
				+ countryId + ", Address: " + homeAddress + ", City: " + homeCity + ", State: " + homeState
				+ ", ZipCode: " + homeZipCode + "]";
	}
	
	

}
