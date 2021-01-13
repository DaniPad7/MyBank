package com.mybank.service;

import com.mybank.exception.BusinessException;

public interface AccountDeleteService {
	public void deleteMaxUserIdFromUserPersonalInfo() throws BusinessException;
	
}
