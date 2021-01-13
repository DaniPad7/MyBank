package com.mybank.service.impl.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserBankHistory;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;
import com.mybank.service.impl.AccountCreateServiceImpl;

class AccountCreateServiceImplTest {
	Logger log =  Logger.getLogger(AccountCreateServiceImplTest.class);
	private static AccountCreateServiceImpl accountCreateServiceImpl;
	private static UserPersonalInfo userPersonalInfoRead;
	private static UserAccountInfo userAccountInfo;
	private static UserCorporateInfo userCorporateInfo;
	private static UserBankHistory userBankHistory;
	@BeforeAll
	public static void setup() {
		accountCreateServiceImpl = new AccountCreateServiceImpl();
	}
	
	@BeforeEach
	public void setupDefaults() {
		userPersonalInfoRead = new UserPersonalInfo();
		userAccountInfo = new UserAccountInfo();
		userCorporateInfo = new UserCorporateInfo();
		userBankHistory =  new UserBankHistory();
	}
	
	@Test
	void testregiCustomerAccountForSQLExceptionDuplicatePassword() {
		Executable executable = new Executable() {
			
			@Override
			public void execute() throws Throwable {
				userPersonalInfoRead = new UserPersonalInfo("milo", "padila", Date.valueOf("2009-09-18"), null, null, 1, "ftggytgtghgt", "dfdfdfdfd", "CA", "92778");
				userCorporateInfo = new UserCorporateInfo(false, "milo23", "BTS");
				try {
					accountCreateServiceImpl.regiCustomerAccount(userPersonalInfoRead, userCorporateInfo);
				} catch (BusinessException e) {
					log.info(e);
				}
				}
			};
			assertThrows(SQLException.class, executable);
	}	
	
	
	
	@Test
	void testOpenNewAccForNull() {
		userPersonalInfoRead = new UserPersonalInfo(null, null, null, null, null, 0, null, null, null, null);
		userAccountInfo = new UserAccountInfo(0, null, 0, 0, 0, false);
		try {
			assertNull(accountCreateServiceImpl.openNewAcc(userPersonalInfoRead, userAccountInfo));
		} catch (BusinessException e) {
			log.info(e);
		}
		
	}
	
	@Test
	void testOpenNewAccForSQLExceptionDoubleToMoneyDataType() {
		Executable executable = new Executable() {

			@Override
			public void execute() throws Throwable {
				userPersonalInfoRead = new UserPersonalInfo("yorki", "padila", Date.valueOf("2009-09-18"), null, null, 1, "ftggytgtghgt", "dfdfdfdfd", "CA", "92778");
				userAccountInfo = new UserAccountInfo(23, "Checkings", 545544, 23, 123.99, false);
				accountCreateServiceImpl.openNewAcc(userPersonalInfoRead, userAccountInfo);
				
			}
			
		};
		assertThrows(SQLException.class, executable);
	}



	@Test
	void testWithdrawOrDepositForEquals1DueToEmptyString() {
		userBankHistory = new UserBankHistory(4, 45, 45, "", 49.99, true);
		userAccountInfo = new UserAccountInfo(23, "Checkings", 545544, 23, 123.99, false);
		try {
			assertEquals(1,accountCreateServiceImpl.withdrawOrDeposit(userBankHistory, userAccountInfo));
		} catch (BusinessException e) {
			log.info(e);
		}
		}
	
	@Test
	void testWithdrawOrDepositForBusinessExceptionDueToBalanceOutofBounds() {
		Executable executable = new Executable() {

			@Override
			public void execute() throws Throwable {
				userBankHistory = new UserBankHistory(4, 56, 56, "Withdraw", 200.00, true);
				userAccountInfo = new UserAccountInfo(23, "Checkings", 545544, 23, 123.99, false);
				
			}
			
		};
		assertThrows(BusinessException.class, executable);
	} 
}