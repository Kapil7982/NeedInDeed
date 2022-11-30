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
import com.NeedInDeed.models.AdminLoginDTO;
import com.NeedInDeed.services.AdminLoginService;

@RestController
@RequestMapping("/admin")
public class AdminLoginController {

	@Autowired
	private AdminLoginService adminLogin;

	@PostMapping("/login")
	public ResponseEntity<String> logInAdmin(@RequestBody AdminLoginDTO dto) throws LoginException {

		String result = adminLogin.logIntoAccount(dto);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@PostMapping("/logout")
	public String logoutAdmin(@RequestParam(required = false) String key) throws LoginException {
		return adminLogin.logOutFromAccount(key);

	}

}
