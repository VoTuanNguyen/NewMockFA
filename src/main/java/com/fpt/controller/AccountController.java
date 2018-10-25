package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.entity.User;
import com.fpt.model.LoginModel;
import com.fpt.service.AccountService;
import com.fpt.service.JwtService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		String passwordEncrypt = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(passwordEncrypt);// encrypt your password before save in database

		return accountService.register(user);
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginModel login) {
		LoginModel loginModel = accountService.login(login.getUsername());
		if (BCrypt.checkpw(login.getPassword(), loginModel.getPassword())) {
			return jwtService.generateTokenLogin(login.getUsername());
		}
		return "KO";
	}
}
