package com.mybank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.mybank.dao.AccountCreateDAO;
import com.mybank.dao.dbutil.PostgresqlConnection;
import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserBankHistory;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;


public class AccountCreateDAOImpl implements AccountCreateDAO{
	
	public static Logger log = Logger.getLogger(AccountCreateDAOImpl.class);

	@Override
	public int regiCustomerAccount(UserPersonalInfo userPersonalInfo, UserCorporateInfo userCorporateInfo) throws BusinessException {
		int c = 0;
		int c0 = 0;
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql1 = "INSERT INTO mybank.user_personal_info(user_id, first_name, last_name, birth_date, email,  phone_number, home_address, home_city, home_zip_code, country_id, home_state) VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?);";
			String sql2 = "INSERT INTO mybank.user_corp_info(user_id, is_employee, username, password) VALUES(DEFAULT,?,?,?)"; 
			//there might be a compilation problem in the query above
			PreparedStatement preparedStatement = connection.prepareStatement(sql1);
			PreparedStatement preparedStatement0 = connection.prepareStatement(sql2);
			
			//
			
			//preparedStatement.setInt(1, userPersonalInfo.getUserId());
			preparedStatement.setString(1, userPersonalInfo.getFirstName());
			preparedStatement.setString(2, userPersonalInfo.getLastName());
			preparedStatement.setDate(3, userPersonalInfo.getBirthDate());
			preparedStatement.setString(4, userPersonalInfo.getEmail());
			preparedStatement.setString(5, userPersonalInfo.getPhoneNumber());
			preparedStatement.setString(6, userPersonalInfo.getHomeAddress());
			preparedStatement.setString(7, userPersonalInfo.getHomeCity());
			preparedStatement.setString(8, userPersonalInfo.getHomeZipCode());
			preparedStatement.setInt(9, userPersonalInfo.getCountryId());
			preparedStatement.setString(10, userPersonalInfo.getHomeState());
			
			//preparedStatement.setInt(12, userPersonalInfo.getUserId());
			preparedStatement0.setBoolean(1, userCorporateInfo.isEmployee());
			preparedStatement0.setString(2, userCorporateInfo.getUsername());
			preparedStatement0.setString(3, userCorporateInfo.getPassword()); 
			
			
			c = preparedStatement.executeUpdate();
			c0 = preparedStatement0.executeUpdate();
			
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e.getMessage());
			throw new BusinessException("");
		}
		return c + c0;
	}

	@Override
	public int openNewAcc(UserPersonalInfo userPersonalInfoRead, UserAccountInfo userAccountInfo) throws BusinessException {
		userAccountInfo.setAccountNumber();
		int c = 0;
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql1 = "INSERT INTO mybank.user_account_info(user_id, account_type, account_number, routing_number, balance,  is_approved) VALUES(?,?,?,DEFAULT,?::float8::numeric::money,false);"; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql1);
			
			preparedStatement.setInt(1, userPersonalInfoRead.getUserId());
			preparedStatement.setString(2, userAccountInfo.getAccountType());
			preparedStatement.setInt(3, userAccountInfo.getAccountNumber());
			preparedStatement.setDouble(4, userAccountInfo.getBalance());
			
			c = preparedStatement.executeUpdate();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e.getMessage());
			throw new BusinessException("");
		}
		return c ;
	}

	@Override
	public int withdrawOrDeposit(UserBankHistory userBankHistory, UserAccountInfo userApprovedAccountInfo) throws BusinessException {
		int c = 0;
		int c0 = 0;
		try {
			
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "INSERT INTO mybank.user_bank_history(user_id, routing_number, routing_number_dest, transaction_type, amount, is_accepted) VALUES(?,?,?,?,?::float8::numeric::money,?);"; 
			String sql1 = "UPDATE mybank.user_account_info SET balance =  ?::float8::numeric::money WHERE routing_number = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			
			preparedStatement.setInt(1, userBankHistory.getUserId());
			preparedStatement.setInt(2, userBankHistory.getRoutingNumber());
			preparedStatement.setInt(3, userBankHistory.getRoutingNumberDest());
			preparedStatement.setString(4, userBankHistory.getTransactionType());
			preparedStatement.setDouble(5, userBankHistory.getAmount());
			preparedStatement.setBoolean(6, userBankHistory.getIsAccepted());
			if(userBankHistory.getTransactionType().equals("Withdrawal")) {
				preparedStatement1.setDouble(1, (userApprovedAccountInfo.getBalance() - userBankHistory.getAmount()));
			}
			else {
				preparedStatement1.setDouble(1, (userApprovedAccountInfo.getBalance() + userBankHistory.getAmount()));
			}
			preparedStatement1.setInt(2, userApprovedAccountInfo.getRoutingNumber());
			c = preparedStatement.executeUpdate();
			c0 = preparedStatement1.executeUpdate();
			connection.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e.getMessage());
			throw new BusinessException("");
		}
		return c + c0;
		}

	@Override
	public int postTransfer(UserBankHistory userBankHistory, UserAccountInfo userApprovedAccountInfo, UserAccountInfo userApprovedAccountInfoDest) throws BusinessException {
		int c = 0;
		int c0 = 0;
		int c1 = 0;
		try {
			
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "INSERT INTO mybank.user_bank_history(user_id, routing_number, routing_number_dest, transaction_type, amount, is_accepted) VALUES(?,?,?,?,?::float8::numeric::money,?);"; 
			String sql1 = "UPDATE mybank.user_account_info SET balance =  ?::float8::numeric::money WHERE routing_number = ?;";
			String sql2 = "UPDATE mybank.user_account_info SET balance =  ?::float8::numeric::money WHERE routing_number = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			
			preparedStatement.setInt(1, userBankHistory.getUserId());
			preparedStatement.setInt(2, userBankHistory.getRoutingNumber());
			preparedStatement.setInt(3, userBankHistory.getRoutingNumberDest());
			preparedStatement.setString(4, userBankHistory.getTransactionType());
			preparedStatement.setDouble(5, userBankHistory.getAmount());
			preparedStatement.setBoolean(6, userBankHistory.getIsAccepted());
			
			preparedStatement1.setDouble(1, (userApprovedAccountInfo.getBalance() - userBankHistory.getAmount()));
			preparedStatement1.setInt(2, userApprovedAccountInfo.getRoutingNumber());
			
			preparedStatement2.setDouble(1, (userApprovedAccountInfoDest.getBalance() + userBankHistory.getAmount()));
			preparedStatement2.setInt(2, userApprovedAccountInfoDest.getRoutingNumber());
			
			c = preparedStatement.executeUpdate();
			c0 = preparedStatement1.executeUpdate();
			c1 = preparedStatement2.executeUpdate();
			connection.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e.getMessage());
			throw new BusinessException("");
		}
		return c + c0 + c1;
		
	}

}
