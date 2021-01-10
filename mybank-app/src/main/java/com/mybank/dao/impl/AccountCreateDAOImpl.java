package com.mybank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mybank.dao.AccountCreateDAO;
import com.mybank.dao.dbutil.PostgresqlConnection;
import com.mybank.exception.BusinessException;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;


public class AccountCreateDAOImpl implements AccountCreateDAO{
	
	public static Logger log = Logger.getLogger(AccountCreateDAOImpl.class);

	@Override
	public int regiCustomerAccount(UserPersonalInfo userPersonalInfo, UserCorporateInfo userCorporateInfo) throws BusinessException {
		int c = 0;
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "INSERT INTO mybank.user_personal_info(user_id, first_name, last_name, birth_date, email,  phone_number, home_address, home_city, home_zip_code, country_id, home_state) VALUES(?,?,?,?,?,?,?,?,?,?,?);"
					+ "INSERT INTO mybank.user_corp_info(user_id, is_employee, username, password) VALUES(?,?,?,?);"; 
			//there might be a compilation problem in the query above
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, userPersonalInfo.getUserId());
			preparedStatement.setString(2, userPersonalInfo.getFirstName());
			preparedStatement.setString(3, userPersonalInfo.getLastName());
			preparedStatement.setDate(4, userPersonalInfo.getBirthDate());
			preparedStatement.setString(5, userPersonalInfo.getEmail());
			preparedStatement.setString(6, userPersonalInfo.getPhoneNumber());
			preparedStatement.setString(7, userPersonalInfo.getHomeAddress());
			preparedStatement.setString(8, userPersonalInfo.getHomeCity());
			preparedStatement.setString(9, userPersonalInfo.getHomeZipCode());
			preparedStatement.setInt(10, userPersonalInfo.getCountryId());
			preparedStatement.setString(11, userPersonalInfo.getHomeState());
			
			preparedStatement.setInt(12, userPersonalInfo.getUserId());
			preparedStatement.setBoolean(13, userCorporateInfo.isEmployee());
			preparedStatement.setString(14, userCorporateInfo.getUsername());
			preparedStatement.setString(15, userCorporateInfo.getPassword());
			
			
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e);
			throw new BusinessException("Internal error occured in IMPL");
		}
		return c;
	}

	@Override
	public void openNewAcc() throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	

}
