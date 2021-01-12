package com.mybank.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mybank.dao.AccountReadDAO;
import com.mybank.dao.dbutil.PostgresqlConnection;
import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;

public class AccountReadDAOImpl implements AccountReadDAO{
	Logger log = Logger.getLogger(AccountReadDAOImpl.class);

	@Override
	public UserPersonalInfo userLogin(String username, String password) throws BusinessException {
		
		UserPersonalInfo userPersonalInfo = new UserPersonalInfo();
	
			Connection connection;
			try {
				connection = PostgresqlConnection.getConnection();
				String sql = "SELECT user_id, first_name, last_name, birth_date, email,  phone_number, home_address, home_city, home_zip_code, country_id, home_state FROM mybank.user_personal_info WHERE user_id IN"
						+ "(SELECT user_id from mybank.user_corp_info WHERE username = ? AND \"password\" = ?);";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					
					userPersonalInfo.setUserId(resultSet.getInt("user_id"));
					userPersonalInfo.setFirstName(resultSet.getString("first_name"));
					userPersonalInfo.setLastName(resultSet.getString("last_name"));
					userPersonalInfo.setBirthDate(resultSet.getDate("birth_date"));;
					userPersonalInfo.setEmail(resultSet.getString("email"));
					userPersonalInfo.setPhoneNumber(resultSet.getString("phone_number"));
					userPersonalInfo.setHomeAddress(resultSet.getString("home_address"));
					userPersonalInfo.setHomeCity(resultSet.getString("home_city"));
					userPersonalInfo.setHomeZipCode(resultSet.getString("home_zip_code"));
					userPersonalInfo.setCountryId(resultSet.getInt("country_id"));
					userPersonalInfo.setHomeState(resultSet.getString("home_state"));
					
					connection.close();
				}
				else {
					throw new BusinessException("The username or password is incorrect. ");
				}
			} catch (ClassNotFoundException | SQLException e) {
				log.info(e);
				throw new BusinessException("Problem in DAOImpl");
			}
			return userPersonalInfo;
		}
	
	
	@Override
	public UserCorporateInfo userGetCorporateInfoIsEmployee(String username, String password) throws BusinessException {
		UserCorporateInfo userCorporateInfo = new UserCorporateInfo();
		
		Connection connection;
		try {
			connection = PostgresqlConnection.getConnection();
			String sql = "SELECT user_id, username, password, is_employee from mybank.user_corp_info WHERE username = ? AND \"password\" = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				
				userCorporateInfo.setUserId(resultSet.getInt("user_id"));
				userCorporateInfo.setEmployee(resultSet.getBoolean("is_employee"));
				
				connection.close();
			}
			else {
				throw new BusinessException("The username or password is incorrect. ");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e);
			throw new BusinessException("Problem in userGetIsEmployee DAOImpl");
		}
		return userCorporateInfo;
	}

	@Override
	public void getAllCustomers() throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLogsOfCustomer() throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserAccountInfo> getAcc(String firstName, String lastName) throws BusinessException {
		List<UserAccountInfo> userAccountInfoList= new ArrayList<>();
		Connection connection;
		try {
			connection = PostgresqlConnection.getConnection();
			String sql = "select user_id, account_type, account_number, balance::money::numeric::float8, is_approved, routing_number from mybank.user_account_info where user_id in \r\n"
					+ "(select user_id from mybank.user_personal_info where first_name = ? and last_name = ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				UserAccountInfo userAccountInfo = new UserAccountInfo();
				userAccountInfo.setUserId(resultSet.getInt("user_id"));
				userAccountInfo.setAccountType(resultSet.getString("account_type"));
				userAccountInfo.setAccountNumber(resultSet.getInt("account_number"));
				userAccountInfo.setBalance(resultSet.getDouble("balance"));
				userAccountInfo.setApproved(resultSet.getBoolean("is_approved"));
				userAccountInfo.setRoutingNumber(resultSet.getInt("routing_number"));
				userAccountInfoList.add(userAccountInfo);
				
			}
			connection.close();
			if(userAccountInfoList.size() == 0) {
				throw new BusinessException("No customers found. " );
			}
			else {}
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e);
			throw new BusinessException("Problem in userAcc DAOImpl");
		}
		return userAccountInfoList;
		
	}
	
	public List<UserAccountInfo> getAcc(String password) throws BusinessException {
		List<UserAccountInfo> userAccountInfoList= new ArrayList<>();
		Connection connection;
		try {
			connection = PostgresqlConnection.getConnection();
			String sql = "select user_id, account_type, account_number, balance::money::numeric::float8, is_approved, routing_number from mybank.user_account_info where user_id in \r\n"
					+ "(select user_id from mybank.user_corp_info where password = ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				UserAccountInfo userAccountInfo = new UserAccountInfo();
				userAccountInfo.setUserId(resultSet.getInt("user_id"));
				userAccountInfo.setAccountType(resultSet.getString("account_type"));
				userAccountInfo.setAccountNumber(resultSet.getInt("account_number"));
				userAccountInfo.setBalance(resultSet.getDouble("balance"));
				userAccountInfo.setApproved(resultSet.getBoolean("is_approved"));
				userAccountInfo.setRoutingNumber(resultSet.getInt("routing_number"));
				userAccountInfoList.add(userAccountInfo);
				
			}
			connection.close();
			if(userAccountInfoList.size() == 0) {
				throw new BusinessException("No customers found. " );
			}
			else {}
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e);
			throw new BusinessException("Problem in userAcc2 DAOImpl");
		}
		return userAccountInfoList;
		
	}

	

}
