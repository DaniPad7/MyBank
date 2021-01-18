package com.mybank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mybank.dao.AccountReadDAO;
import com.mybank.dao.impl.AccountReadDAOImpl;
import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;
import com.mybank.service.AccountReadService;

public class AccountReadServiceImpl implements AccountReadService{
	private AccountReadDAO accountReadDAO = new AccountReadDAOImpl();
	Logger log = Logger.getLogger(AccountReadServiceImpl.class);

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
			throw new BusinessException("No customers found or customer does not have unapproved accounts. " );
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
			throw new BusinessException("No Accounts found. The approval of your accounts are still pending. " );
		}
		else {}
		return userAccountInfoList;
		}


	@Override
	public List<UserAccountInfo> getApprovedAccByCorp(String username, String password) throws BusinessException {
		List<UserAccountInfo> userAccountInfoList = null;
		List<UserAccountInfo> approvedUserAccountInfoList = new ArrayList<>();
		if(username.matches("^[a-zA-Z0-9]+$") && password.matches("^[a-zA-Z0-9]+$")) {
			userAccountInfoList = accountReadDAO.getApprovedAccByCorp(username, password);	}
		else {
			throw new BusinessException("The username or password is invalid. Please try again.");
		}
		if(userAccountInfoList != null && userAccountInfoList.size() > 0) {
			log.info("Your approved accounts are printed below:");
			for(UserAccountInfo uai : userAccountInfoList) {
				if(uai.isApproved()) {
					log.info(uai);
					approvedUserAccountInfoList.add(uai);
				}
				}
			if(approvedUserAccountInfoList.size() == 0) {
				throw new BusinessException("The customer either has pending or non approved accounts.");
			}
			
		}
		else if(userAccountInfoList.size() == 0) {
			throw new BusinessException("No customers found or customer does not have unapproved accounts. " );
		}
		else {}
		
		return approvedUserAccountInfoList;
	}
	

	

}
