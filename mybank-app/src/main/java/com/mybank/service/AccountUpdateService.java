package com.mybank.service;

import com.mybank.exception.BusinessException;

public interface AccountUpdateService {
	public int approveAcc(int routingNumber) throws BusinessException;
	public void postTransfer() throws BusinessException;
	public void acceptTransfer() throws BusinessException;
}
