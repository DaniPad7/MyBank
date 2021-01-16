package com.mybank.service.impl.test;

import static org.junit.jupiter.api.Assertions.*;


import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.mybank.exception.BusinessException;
import com.mybank.service.AccountDeleteService;
import com.mybank.service.impl.AccountDeleteServiceImpl;

class AccountDeleteServiceImplTest {
	Logger log = Logger.getLogger(AccountDeleteServiceImplTest.class);
	private static AccountDeleteService accountDeleteServiceImpl;

	@BeforeAll
	public static void setup() {
		accountDeleteServiceImpl = new AccountDeleteServiceImpl();
	}
	
	
	@Test
	void testDeleteMaxUserIdFromUserPersonalInfoForExceptionDueToForeignKeyViolation() {
		Executable executable = new Executable() {

			@Override
			public void execute() throws Throwable {
				try {
					accountDeleteServiceImpl.deleteMaxUserIdFromUserPersonalInfo();
				} catch (BusinessException e) {
					log.info(e);
				}
				
			}
			
		}; assertDoesNotThrow(executable);
		
	}

}
