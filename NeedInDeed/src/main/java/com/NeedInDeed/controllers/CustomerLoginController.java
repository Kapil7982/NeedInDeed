package com.NeedInDeed.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.NeedInDeed.exceptions.LoginException;
import com.NeedInDeed.models.CustomerLoginDTO;
import com.NeedInDeed.services.CustomerLoginService;

@RestController
@RequestMapping("/customer")
public class CustomerLoginController {

	@Autowired
	private CustomerLoginService customerLogin;

	@PostMapping("/login")
	public ResponseEntity<String> logInCustomer(@RequestBody CustomerLoginDTO dto) throws LoginException {

		String result = customerLogin.logIntoAccount(dto);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@PostMapping("/logout")
	public String logoutCustomer(@RequestParam(required = false) String key) throws LoginException {
		return customerLogin.logOutFromAccount(key);

	}

}
