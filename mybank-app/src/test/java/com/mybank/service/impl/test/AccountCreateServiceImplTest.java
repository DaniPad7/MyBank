package com.mybank.service.impl.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserPersonalInfo;
import com.mybank.service.impl.AccountCreateServiceImpl;

class AccountCreateServiceImplTest {
	Logger log =  Logger.getLogger(AccountCreateServiceImplTest.class);
	public static AccountCreateServiceImpl accountCreateServiceImpl;
	private static UserPersonalInfo userPersonalInfoRead;
	private static UserAccountInfo userAccountInfo;
	@BeforeAll
	public static void setup() {
		accountCreateServiceImpl = new AccountCreateServiceImpl();
	}
	
	@BeforeAll
	public static void setupNulls() {
		userPersonalInfoRead = new UserPersonalInfo();
		userAccountInfo = new UserAccountInfo();
	}

	
	@Test
	void testOpenNewAccForNull() {
		try {
			assertNull(accountCreateServiceImpl.openNewAcc(userPersonalInfoRead, userAccountInfo));
		} catch (BusinessException e) {
			log.info(e);
		}
		
	}
	
	@Test
	void testOpenNewAccForSQLException() {
		Executable executable = new Executable() {

			@Override
			public void execute() throws Throwable {
				accountCreateServiceImpl.openNewAcc(userPersonalInfoRead, userAccountInfo);
				
			}
			
		};
		assertThrows(SQLException.class, executable);
	}

}
