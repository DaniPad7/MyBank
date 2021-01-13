package com.mybank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mybank.dao.AccountDeleteDAO;
import com.mybank.dao.dbutil.PostgresqlConnection;
import com.mybank.exception.BusinessException;

public class AccountDeleteDAOImpl implements AccountDeleteDAO{
	
	public static Logger log = Logger.getLogger(AccountDeleteDAOImpl.class);

	@Override
	public void deleteMaxUserIdFromUserPersonalInfo() throws BusinessException {
		Connection connection;
		try {
			connection = PostgresqlConnection.getConnection();
			String sql3 = "DELETE FROM mybank.user_personal_info WHERE user_id IN"
					+ "(SELECT max(user_id) FROM mybank.user_personal_info);";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql3);
			preparedStatement1.executeUpdate();
				
			connection.close();
		}catch (ClassNotFoundException | SQLException e) {
			log.info(e);
			log.info("Problem in AccountDeleteDAO maybe. Also deelete ran");
			}

	}
}