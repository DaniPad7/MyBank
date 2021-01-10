package com.mybank.model;

public class UserAccountInfo {
	private int userId;
	private String accountType;
	private int accountNumber;
	private int routingNumber;
	private double balance;
	private boolean isApproved;
	
	
	public UserAccountInfo() {
		super();
	}


	public UserAccountInfo(int userId, String accountType, int accountNumber, int routingNumber, double balance,
			boolean isApproved) {
		super();
		this.userId = userId;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.routingNumber = routingNumber;
		this.balance = balance;
		this.isApproved = isApproved;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public int getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}


	public int getRoutingNumber() {
		return routingNumber;
	}


	public void setRoutingNumber(int routingNumber) {
		this.routingNumber = routingNumber;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public boolean isApproved() {
		return isApproved;
	}


	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}


	@Override
	public String toString() {
		return "UserAccountInfo [userId=" + userId + ", accountType=" + accountType + ", accountNumber=" + accountNumber
				+ ", routingNumber=" + routingNumber + ", balance=" + balance + ", isApproved=" + isApproved + "]";
	}
	
	
	
	
	

}
