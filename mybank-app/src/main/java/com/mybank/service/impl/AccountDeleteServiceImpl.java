package com.mybank.service.impl;

import com.mybank.dao.AccountDeleteDAO;
import com.mybank.dao.impl.AccountDeleteDAOImpl;
import com.mybank.exception.BusinessException;
import com.mybank.service.AccountDeleteService;

public class AccountDeleteServiceImpl implements AccountDeleteService{
	private AccountDeleteDAO accountDeleteDAO = new AccountDeleteDAOImpl();

	@Override
	public void deleteMaxUserIdFromUserPersonalInfo() throws BusinessException {
		accountDeleteDAO.deleteMaxUserIdFromUserPersonalInfo();
		
	}

}
