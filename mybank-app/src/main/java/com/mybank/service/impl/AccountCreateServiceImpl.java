package com.mybank.service.impl;

import java.sql.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.mybank.dao.AccountCreateDAO;
import com.mybank.dao.impl.AccountCreateDAOImpl;
import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserBankHistory;
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
			throw new BusinessException("");
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
			throw new BusinessException("");
		}
		return c;
	}

	@Override
	public int withdrawOrDeposit(UserBankHistory userBankHistory,UserAccountInfo userApprovedAccountInfo) throws BusinessException {
		int c = 0;
		if((userBankHistory.getTransactionType().equals("Withdrawal") && (userApprovedAccountInfo.getBalance() - userBankHistory.getAmount()) > -1) || (userBankHistory.getTransactionType().equals("Deposit") && (userApprovedAccountInfo.getBalance() + userBankHistory.getAmount()) < 10_000_000)){
			c = accountCreateDAO.withdrawOrDeposit(userBankHistory,userApprovedAccountInfo);
			if(c != 0 && userBankHistory != null) {
				log.info("Transaction success. Redirecting you back.");
			}
			else {
				throw new BusinessException("");
			}
		}else {
			throw new BusinessException("The Account balance will be out of bounds after this transaction.");
		}
		return c;
	}

	@Override
	public int postTransfer(UserBankHistory userBankHistory, UserAccountInfo userApprovedAccountInfo,
			UserAccountInfo userApprovedAccountInfoDest) throws BusinessException {
		int c = 0;
		if(userApprovedAccountInfo.getBalance() - userBankHistory.getAmount() > -1 && userApprovedAccountInfoDest.getBalance() + userBankHistory.getAmount() < 10_000_000){
			c = accountCreateDAO.postTransfer(userBankHistory,userApprovedAccountInfo, userApprovedAccountInfoDest);
			if(c != 0 && userBankHistory != null && userApprovedAccountInfo != null && userApprovedAccountInfoDest != null) {
				log.info("Transaction success. Redirecting you back.");
			}
			else {
				throw new BusinessException("");
			}
		}else {
			throw new BusinessException("The Account balance will be out of bounds after this transaction.");
		}
		return c;
	}

}
