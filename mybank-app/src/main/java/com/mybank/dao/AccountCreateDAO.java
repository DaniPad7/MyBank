package com.mybank.dao;

import com.mybank.exception.BusinessException;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;

public interface AccountCreateDAO {
	
	public int regiCustomerAccount(UserPersonalInfo userPersonalInfo, UserCorporateInfo userCorporateInfo) throws BusinessException;
	public void openNewAcc() throws BusinessException;

}
