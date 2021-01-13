package com.mybank.service.impl;

import java.util.List;

import com.mybank.dao.AccountReadDAO;
import com.mybank.dao.impl.AccountReadDAOImpl;
import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;
import com.mybank.service.AccountReadService;

public class AccountReadServiceImpl implements AccountReadService{
	private AccountReadDAO accountReadDAO = new AccountReadDAOImpl();

	@Override
	public UserPersonalInfo userLogin(String username, String password) throws BusinessException {
		UserPersonalInfo userPersonalInfo = null;
		if(username.matches("^[a-zA-Z0-9]+$") && password.matches("^[a-zA-Z0-9]+$")) {
			userPersonalInfo = accountReadDAO.userLogin(username, password);		} //service layer calls DAO layer
		else {
			throw new BusinessException("The username or password is invalid");
		}
		return userPersonalInfo;
	}
	
	
	@Override
	public UserCorporateInfo userGetCorporateInfoIsEmployee(String username, String password) throws BusinessException {
		UserCorporateInfo userCorporateInfo= null;
		if(username.matches("^[a-zA-Z0-9]+$") && password.matches("^[a-zA-Z0-9]+$")) {
			userCorporateInfo = accountReadDAO.userGetCorporateInfoIsEmployee(username, password);	}
		else {
			throw new BusinessException("The username or password is invalid");
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
		List<UserAccountInfo> userAccountInfoList = null;
		if(firstName.matches("^[a-zA-Z]+$") && lastName.matches("^[a-zA-Z]+$")) {
			userAccountInfoList = accountReadDAO.getAcc(firstName, lastName);	}
		else {
			throw new BusinessException("The first or last name is invalid. Please try again.");
		}
		if(userAccountInfoList.size() == 0) {
			throw new BusinessException("No customers found. " );
		}
		else {}
		
		return userAccountInfoList;
		}
	
	public List<UserAccountInfo> getAcc(String password) throws BusinessException {
		List<UserAccountInfo> userAccountInfoList = null;
		if(password.matches("^[a-zA-Z0-9]+$")) {
			userAccountInfoList = accountReadDAO.getAcc(password);	}
		else {
			throw new BusinessException("The password is invalid. Please try again.");
		}
		if(userAccountInfoList.size() == 0) {
			throw new BusinessException("No Accounts found. Make sure you aporove your accounts with an Employee! " );
		}
		else {}
		return userAccountInfoList;
		}
	

	

}
