package com.mybank.dao;

import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserBankHistory;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;

public interface AccountCreateDAO {
	
	public int regiCustomerAccount(UserPersonalInfo userPersonalInfo, UserCorporateInfo userCorporateInfo) throws BusinessException;
	public int openNewAcc(UserPersonalInfo userPersonalInfoRead, UserAccountInfo userAccountInfo) throws BusinessException;
	public int withdrawOrDeposit(UserBankHistory userBankHistory, UserAccountInfo userApprovedAccountInfo) throws BusinessException;
	public int postTransfer(UserBankHistory userBankHistory, UserAccountInfo userApprovedAccountInfo, UserAccountInfo userApprovedAccountInfoDest ) throws BusinessException;
}
