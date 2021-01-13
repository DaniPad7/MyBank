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
			log.info(e);
			throw new BusinessException("You are already registered with us. Internal error occured in IMPL");
		}
		return c + c0;
	}

	@Override
	public int openNewAcc(UserPersonalInfo userPersonalInfoRead, UserAccountInfo userAccountInfo) throws BusinessException {
		userAccountInfo.setAccountNumber();
		log.info(userAccountInfo.getAccountType());
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
			log.info(e);
			throw new BusinessException("The account already exists. Internal error occured in IMPL");
		}
		return c ;
	}

	

}
