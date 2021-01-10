package com.mybank.dao;

import com.mybank.exception.BusinessException;

public interface AccountReadDAO {
	
	public void userLogin() throws BusinessException;
	public void getAllCustomers() throws BusinessException;
	public void getLogsOfCustomer() throws BusinessException;
	public void getAcc() throws BusinessException;

}
