package com.mybank.service.impl.test;

import static org.junit.jupiter.api.Assertions.*;



import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.mybank.exception.BusinessException;
import com.mybank.service.impl.AccountReadServiceImpl;

class AccountReadServiceImplTest {
	private static AccountReadServiceImpl accountReadServiceImpl;
	Logger log = Logger.getLogger(AccountReadServiceImpl.class);
	
	@BeforeAll
	public static void setup() {
		accountReadServiceImpl = new AccountReadServiceImpl();
	}

	@Test
	void testUserLoginForSQLException() {
		Executable executable = new Executable() {

			@Override
			public void execute() throws Throwable {
				accountReadServiceImpl.userLogin("Available", "Non-existent");
				
			}
			
		};
		assertThrows(BusinessException.class, executable);
		
		
	}
	@Test
	void testUserLoginForBusinessException() {
		Executable executable = new Executable() {

			@Override
			public void execute() throws Throwable {
				accountReadServiceImpl.userLogin("Invalid&&^", "oldone3");
				
			}
			
		};
		assertThrows(BusinessException.class, executable);
	}
	
	@Test
	void testUserGetCorporateInfoIsEmployeeForBusinessExceptionInvalidPassword() {
		Executable executable = new Executable() {

			@Override
			public void execute() throws Throwable {
				accountReadServiceImpl.userGetCorporateInfoIsEmployee("Available", "Non-existent");
				
			}
			
		};
		assertThrows(BusinessException.class, executable);
	}
		
	@Test
	void testUserGetCorporateInfoIsEmployeeForBusinessExceptionInvalidUsername() {
	Executable executable = new Executable() {

		@Override
		public void execute() throws Throwable {
			accountReadServiceImpl.userGetCorporateInfoIsEmployee("Invalid&&^", "oldone3");
			
		}
		
	};
	assertThrows(BusinessException.class, executable);
	}
	
	/*@Test
	void testGetAccForException() {
		
	}*/
	
	@Test 
	void testGetAccForBusinessExceptionNoQueryResult() {
		Executable executable = new Executable() {

			@Override
			public void execute() throws Throwable {
				
					accountReadServiceImpl.getAcc("NoResult");
				
				}
			};assertThrows(BusinessException.class, executable);
		
	}
}
