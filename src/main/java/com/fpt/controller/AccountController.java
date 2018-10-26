package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.entity.User;
import com.fpt.model.LoginModel;
import com.fpt.model.Message;
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

	@Autowired
	private BCryptPasswordEncoder encrypt;

	@PostMapping("/register")
	public Message register(@RequestBody User user) {
		String passwordEncrypt = encrypt.encode(user.getPassword());
		user.setPassword(passwordEncrypt);// encrypt your password before save in database
		try {
			if (accountService.register(user) != null) {
				return new Message("OK");
			}
		} catch (Exception e) {
			return new Message(e.getMessage());
		}
		return new Message("KO");
	}

	@PostMapping("/login")
	public Message login(@RequestBody LoginModel login) {
		LoginModel loginModel = accountService.login(login.getUsername());
		if (loginModel != null) {
			if (encrypt.matches(login.getPassword(), loginModel.getPassword())) {
				return new Message(jwtService.generateTokenLogin(login.getUsername()));
			}
		}
		return new Message("KO");
	}

	@GetMapping("/checkemail/{email}")
	public Message checkEmail(@PathVariable String email) {
		return new Message(accountService.checkEmail(email) ? "KO" : "OK");
	}

	@GetMapping("/checkusername/{username}")
	public Message checkUsername(@PathVariable String username) {
		return new Message(accountService.checkUsername(username) ? "KO" : "OK");
	}
	
	@GetMapping("/refreshtoken/{token}")
	public Message refreshToken(@PathVariable String token) {
		return new Message(jwtService.refreshToken(token));
	}
	
	@RequestMapping("")
	public Message aa() {
		return new Message("OKOK");
	}
}
