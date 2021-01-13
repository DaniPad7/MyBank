package com.mybank.model;

public class UserBankHistory {
	private int userId;
	private int routingNumber;
	private int routingNumberDest;
	private String transactionType;
	private double amount;
	
	public UserBankHistory() {
		super();
	}
	
	public UserBankHistory(int userId, int routingNumber, int routingNumberDest, String transactionType, double amount) {
		super();
		this.userId = userId;
		this.routingNumber = routingNumber;
		this.routingNumberDest = routingNumberDest;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(int routingNumber) {
		this.routingNumber = routingNumber;
	}

	public int getRoutingNumberDest() {
		return routingNumberDest;
	}

	public void setRoutingNumberDest(int routingNumberDest) {
		this.routingNumberDest = routingNumberDest;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public void setAmount( double amount) {
		this.amount =amount;
	}
	
	public double getAmount() {
		return amount;
	}

	public void setUserId(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "UserBankHistory [ID: " + userId + ", Account Routing Number: " + routingNumber + ", Destination Routing Number"
				+ routingNumberDest + ", Transaction: " + transactionType + ", Amount: " + amount + "]";
	}
	
	

}
