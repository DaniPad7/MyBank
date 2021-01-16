package com.mybank.service.impl.test;

import static org.junit.jupiter.api.Assertions.*;


import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.mybank.exception.BusinessException;
import com.mybank.service.impl.AccountUpdateServiceImpl;

class AccountUpdateServiceImplTest {
private static AccountUpdateServiceImpl accountUpdateServiceImpl;
private static int routingNumber;

Logger log = Logger.getLogger(AccountUpdateServiceImplTest.class);
	
	@BeforeAll
	public static void setup() {
		accountUpdateServiceImpl = new AccountUpdateServiceImpl();
	}
	@AfterEach
	public void setupDefaultInt() {
		routingNumber  = 0;
	}
	
	
	/*@Test
	void testApproveAccForReturnOneAlreadyUpdatedApproval() {
		routingNumber = 9;
		try {
			assertEquals(1, accountUpdateServiceImpl.approveAcc(routingNumber));
		} catch (BusinessException e) {
			log.info(e);
		}
	}*/
	
	@Test
	void testApproveAccForReturnZeroDidNothing() {
		routingNumber = 1;
		try {
			assertEquals(0, accountUpdateServiceImpl.approveAcc(routingNumber));
		} catch (BusinessException e) {
			log.info(e);
		}
	}
	
	@Test
	void testApproveAccForBusinessExceptionInvalidRoutingNumber() {
		routingNumber = -3443;
		Executable executable = new Executable() {

			@Override
			public void execute() throws Throwable {
				accountUpdateServiceImpl.approveAcc(routingNumber);
				
			}
			
		};
		assertThrows(BusinessException.class, executable);
	}

}
