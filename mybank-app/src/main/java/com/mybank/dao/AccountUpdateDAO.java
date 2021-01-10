package com.mybank.dao;

import com.mybank.exception.BusinessException;

public interface AccountUpdateDAO {
	public void approveAcc() throws BusinessException;
	public void withdrawOrDeposit() throws BusinessException;
	public void postTransfer() throws BusinessException;
	public void acceptTransfer() throws BusinessException;

}
