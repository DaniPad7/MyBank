package com.mybank.dao;

import java.util.List;

import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;

public interface AccountReadDAO {
	
	public UserPersonalInfo userLogin(String username, String password) throws BusinessException;
	public UserCorporateInfo userGetCorporateInfoIsEmployee(String username, String password) throws BusinessException;
	public void getAllCustomers() throws BusinessException;
	public void getLogsOfCustomer() throws BusinessException;
	public List<UserAccountInfo> getAcc(String firstName, String lastName) throws BusinessException;
	public List<UserAccountInfo> getAcc(String password) throws BusinessException;
	public List<UserAccountInfo> getApprovedAccByCorp(String username, String password) throws BusinessException;
	

}
