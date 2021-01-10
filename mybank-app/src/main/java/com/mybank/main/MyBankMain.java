package com.mybank.main;

import org.apache.log4j.Logger;

import java.sql.Date;

import com.mybank.exception.BusinessException;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;
import com.mybank.service.AccountCreateService;
import com.mybank.service.impl.AccountCreateServiceImpl;

public class MyBankMain {
	public static Logger log = Logger.getLogger(MyBankMain.class);

	public static void main(String[] args) {
		AccountCreateService accountCreateService = new AccountCreateServiceImpl();
		
		UserPersonalInfo firstUser = new UserPersonalInfo(55, "fdfd", "fdfdh", Date.valueOf("1992-02-18"), "rrf@fgf.com", "434-543-2465", 1, "ddfdf", "dfdsds", "CA", "543245");
		UserCorporateInfo firstUserC = new UserCorporateInfo(55, false, "fdfdsdr", "tgfrgrrg");
		//this works. now let's modify constructor and add aggregate function in sql query
		try {
			int b = accountCreateService.regiCustomerAccount(firstUser, firstUserC);
		} catch (BusinessException e) {
			log.info(e);
		}
		
		
	}

}
