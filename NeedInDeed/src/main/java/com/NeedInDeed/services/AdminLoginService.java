package com.NeedInDeed.services;

import com.NeedInDeed.exceptions.LoginException;
import com.NeedInDeed.models.AdminLoginDTO;

public interface AdminLoginService {

	public String logIntoAccount(AdminLoginDTO dto) throws LoginException;

	public String logOutFromAccount(String key) throws LoginException;

}
