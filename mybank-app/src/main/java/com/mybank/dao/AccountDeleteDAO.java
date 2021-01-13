package com.mybank.dao;

import com.mybank.exception.BusinessException;

public interface AccountDeleteDAO {
	public void deleteMaxUserIdFromUserPersonalInfo() throws BusinessException;

}
