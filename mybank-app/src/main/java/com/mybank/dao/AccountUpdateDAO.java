package com.mybank.dao;

import com.mybank.exception.BusinessException;

public interface AccountUpdateDAO {
	public int approveAcc(int routingNumber) throws BusinessException;
	public void postTransfer() throws BusinessException;
	public void acceptTransfer() throws BusinessException;

}
