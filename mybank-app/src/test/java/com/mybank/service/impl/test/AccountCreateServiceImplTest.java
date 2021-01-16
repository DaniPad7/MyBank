package com.mybank.service.impl.test;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.Date;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserBankHistory;
import com.mybank.model.UserPersonalInfo;
import com.mybank.service.impl.AccountCreateServiceImpl;

class AccountCreateServiceImplTest {
	Logger log =  Logger.getLogger(AccountCreateServiceImplTest.class);
	private static AccountCreateServiceImpl accountCreateServiceImpl;
	private static UserPersonalInfo userPersonalInfoRead;
	private static UserAccountInfo userAccountInfo;
	private static UserBankHistory userBankHistory;
	@BeforeAll
	public static void setup() {
		accountCreateServiceImpl = new AccountCreateServiceImpl();
	}
	
	@BeforeEach
	public void setupDefaults() {
		userPersonalInfoRead = new UserPersonalInfo();
		userAccountInfo = new UserAccountInfo();
		userBankHistory =  new UserBankHistory();
	}
	
	/*@Test
	void testregiCustomerAccountForSQLExceptionDuplicatePassword() {
		Executable executable = new Executable() {
			
			@Override
			public void execute() throws Throwable {
				userPersonalInfoRead = new UserPersonalInfo("milo", "padila", Date.valueOf("2009-09-18"), null, null, 1, "ftggytgtghgt", "dfdfdfdfd", "CA", "92778");
				userCorporateInfo = new UserCorporateInfo(false, "milo23", "Helow");
				try {
					accountCreateServiceImpl.regiCustomerAccount(userPersonalInfoRead, userCorporateInfo);
				} catch (BusinessException e) {
					}
				}
			};
			assertThrows(BusinessException.class, executable);
	}	*/
	
	
	
	@Test
	void testOpenNewAccForCatchExceptionNoNullConstraint() {
		Executable executable = new Executable() {

			@Override
			public void execute() throws Throwable {
				userPersonalInfoRead = new UserPersonalInfo("Yami", "Padila", Date.valueOf("2001-04-21"), null, "234-343-3543", 1, "fvfcvvgfdvv", "fdfvdvd", "CA", "92707");
				userPersonalInfoRead.setUserId(8);
				userAccountInfo = new UserAccountInfo(userPersonalInfoRead.getUserId(), null, 20021, 41, 10, false);
				try {
					assertNull(accountCreateServiceImpl.openNewAcc(userPersonalInfoRead, userAccountInfo));
				} catch (BusinessException e) {
					log.info(e);
				}
				
			}
			
		}; assertDoesNotThrow(executable);
		
		
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
		assertThrows(BusinessException.class, executable);
	}



	@Test
	void testWithdrawOrDepositForEquals1DueToEmptyStringNoNull() {
		userAccountInfo = new UserAccountInfo(23, "Checkings", 545544, 23, 123.99, false);
		userBankHistory = new UserBankHistory(4, 45, 45, "", 49.99, true);
		try {
			assertEquals(1,accountCreateServiceImpl.withdrawOrDeposit(userBankHistory, userAccountInfo));
		} catch (BusinessException e) {
			log.info(e);
		}
		}
	
	@Test
	void testWithdrawOrDepositForBusinessExceptionDueToBalanceOutOfBounds() {
		Executable executable = new Executable() {

			@Override
			public void execute() throws Throwable {
				userBankHistory = new UserBankHistory(4, 56, 56, "Withdraw", 200.00, true);
				userAccountInfo = new UserAccountInfo(23, "Checkings", 545544, 23, 123.99, false);
				accountCreateServiceImpl.withdrawOrDeposit(userBankHistory, userAccountInfo);
			}
			
		};
		assertThrows(BusinessException.class, executable);
	} 
}