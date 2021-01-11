package com.mybank.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


import java.sql.Date;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mybank.exception.BusinessException;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;
import com.mybank.service.AccountCreateService;
import com.mybank.service.impl.AccountCreateServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AccountCreateServiceImplTest {
	
	Logger log = Logger.getLogger(AccountCreateServiceImplTest.class);
	
	AccountCreateService accountCreateServiceImpl;
	UserPersonalInfo userPersonalInfo;
	UserCorporateInfo userCorporateInfo;
	
	@Before
	public void setup() {
		accountCreateServiceImpl = new AccountCreateServiceImpl();
		//declare objects
		userPersonalInfo = Mockito.mock(UserPersonalInfo.class);
		userCorporateInfo = Mockito.mock(UserCorporateInfo.class);
		//no need for scanner in main
		when(userPersonalInfo.getFirstName()).thenReturn("First");
		when(userPersonalInfo.getLastName()).thenReturn("Last");
		when(userPersonalInfo.getBirthDate()).thenReturn(Date.valueOf("1998-08-21"));
		when(userPersonalInfo.getEmail()).thenReturn(null);
		when(userPersonalInfo.getPhoneNumber()).thenReturn(null);
		when(userPersonalInfo.getHomeAddress()).thenReturn("S Honolulu Ave");
		when(userPersonalInfo.getHomeCity()).thenReturn("Honolulu");
		when(userPersonalInfo.getHomeZipCode()).thenReturn("85927");
		when(userPersonalInfo.getCountryId()).thenReturn(Integer.parseInt("1"));
		when(userPersonalInfo.getHomeState()).thenReturn("HI");
		
		when(userCorporateInfo.getUsername()).thenReturn("newone");
		when(userCorporateInfo.getPassword()).thenReturn("oldone3");
		when(userCorporateInfo.isEmployee()).thenReturn(true);
		
		/*this.userPersonalInfo = userPersonalInfo;
		this.userCorporateInfo = userCorporateInfo;*/
	}
	
	/*@Test
	public void testRegiCustomerAccountForGoodValues() {
		
		try {
			assertEquals(2, accountCreateServiceImpl.regiCustomerAccount(userPersonalInfo, userCorporateInfo));
		} catch (BusinessException e) {
			log.info(e);
		}
	}
	
	@Test
	public void testRegiCustomerAccountForExceptionBadNullValue() {
		Executable executable = new Executable() {

			@Override
			public void execute() throws Throwable {
				accountCreateServiceImpl.regiCustomerAccount(userPersonalInfo, userCorporateInfo);
				
			}
			
		};
		assertThrows(BusinessException.class, executable);
	}*/
	
	@Test
	public void testRegiCustomerAccountForGoodNullValue() {
		try {
			assertEquals(2, accountCreateServiceImpl.regiCustomerAccount(userPersonalInfo, userCorporateInfo));
		} catch (BusinessException e) {
			log.info(e);
		}
	}
}
