package com.NeedInDeed.services;

import com.NeedInDeed.exceptions.LoginException;
import com.NeedInDeed.models.CustomerLoginDTO;

public interface CustomerLoginService {

	public String logIntoAccount(CustomerLoginDTO dto) throws LoginException;

	public String logOutFromAccount(String key) throws LoginException;

}
