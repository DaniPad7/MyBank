package com.mybank.model;

public class UserCorporateInfo {
	private int userId;
	private boolean isEmployee;
	private String username;
	private String password;
	
	public UserCorporateInfo() {
		super();
	}

	public UserCorporateInfo(/*int userId,*/ boolean isEmployee, String username, String password) {
		super();
		/*this.userId = userId;*/
		this.isEmployee = isEmployee;
		this.username = username;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserCorporateInfo [userId=" + userId + ", isEmployee=" + isEmployee + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	

}
