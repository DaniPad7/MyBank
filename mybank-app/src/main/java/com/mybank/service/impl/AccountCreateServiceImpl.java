package com.mybank.service.impl;

import java.sql.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.mybank.dao.AccountCreateDAO;
import com.mybank.dao.impl.AccountCreateDAOImpl;
import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;
import com.mybank.service.AccountCreateService;

public class AccountCreateServiceImpl implements AccountCreateService{
	public static Logger log = Logger.getLogger(AccountCreateServiceImpl.class);
	private AccountCreateDAO accountCreateDAO = new AccountCreateDAOImpl();
	Scanner scanner;

	@Override
	public int regiCustomerAccount(UserPersonalInfo userPersonalInfo, UserCorporateInfo userCorporateInfo) throws BusinessException {
		int c = 0;
		c = accountCreateDAO.regiCustomerAccount(userPersonalInfo, userCorporateInfo);
		if(c != 0 && userCorporateInfo != null) {
			log.info("Customer registration success");
		}
		else {
			throw new BusinessException("There is trouble here in AccountCreateServiceIMPL");
		}
		return c;
	}

	@Override
	public int openNewAcc(UserPersonalInfo userPersonalInfoRead, UserAccountInfo userAccountInfo) throws BusinessException {
		int c = 0;
		c = accountCreateDAO.openNewAcc(userPersonalInfoRead,userAccountInfo);
		if(c != 0 && userPersonalInfoRead != null && userAccountInfo != null) {
			log.info("Account registration success");
		}
		else {
			throw new BusinessException("There is trouble here in AccountCreateServiceIMPL");
		}
		return c;
	}

}
