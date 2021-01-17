package com.mybank.service.impl;

import org.apache.log4j.Logger;

import com.mybank.dao.AccountUpdateDAO;
import com.mybank.dao.impl.AccountUpdateDAOImpl;
import com.mybank.exception.BusinessException;
import com.mybank.service.AccountUpdateService;

public class AccountUpdateServiceImpl implements AccountUpdateService{
	AccountUpdateDAO accountUpdateDAO = new AccountUpdateDAOImpl();
	Logger log = Logger.getLogger(AccountUpdateServiceImpl.class);
	@Override
	public int approveAcc(int routingNumber) throws BusinessException {
		int c = 0;
		if (routingNumber < 3_000_000 && routingNumber > -1) {
			c = accountUpdateDAO.approveAcc(routingNumber);
			if(c == 1) {
				log.info("The Account was approved. Redirecting you back.");
			}
			else {}
			}
		else {
			throw new BusinessException("The Routing Number is invalid. Please try again.");
		}
		return c;
	}


	@Override
	public void acceptTransfer() throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}
