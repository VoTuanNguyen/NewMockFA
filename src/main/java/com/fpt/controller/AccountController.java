package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public String register(@RequestBody User user) {
		String passwordEncrypt = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(passwordEncrypt);// encrypt your password before save in database
		try {
			if (accountService.register(user) != null) {
				return "{\"OK\": \"OK\"}";
			}
		} catch (Exception e) {
			return "{\"OK\": \""+e.getMessage()+"\"}";
		}
		return "{\"OK\": \"KO\"}";
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
