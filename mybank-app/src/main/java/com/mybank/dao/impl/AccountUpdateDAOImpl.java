package com.mybank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mybank.dao.AccountUpdateDAO;
import com.mybank.dao.dbutil.PostgresqlConnection;
import com.mybank.exception.BusinessException;

public class AccountUpdateDAOImpl implements AccountUpdateDAO{
	Logger log = Logger.getLogger(AccountUpdateDAOImpl.class);

	@Override
	public int approveAcc(int routingNumber) throws BusinessException {
		int c = 0;
		
		Connection connection;
		try {
			connection = PostgresqlConnection.getConnection();
			String sql = "UPDATE mybank.user_account_info SET is_approved = true WHERE routing_number = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, routingNumber);
			c = preparedStatement.executeUpdate();
		
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e.getMessage());
			throw new BusinessException("");
		} 
		return c;
	}

	@Override
	public void postTransfer() throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void acceptTransfer() throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}
