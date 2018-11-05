package com.fpt.controller;

import java.util.List;

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
import com.fpt.model.MessageLogin;
import com.fpt.repository.UserRepository;
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

	// register account by user
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

	// login
	@PostMapping("/login")
	public MessageLogin login(@RequestBody LoginModel login) {
		User u = accountService.login(login.getUsername());
		if (u != null) {
			if (encrypt.matches(login.getPassword(), u.getPassword())) {
				String token = jwtService.generateTokenLogin(login.getUsername());
				String url = "/home";
				switch (u.getRole().getId()) {
				case 1:
					url = "/home";
					break;
				case 2:
					url = "/staff";
					break;
				case 3:
					url = "/admin";
					break;
				default:
					url = "/home";
				}
				return new MessageLogin(token, url);
			}
		}
		return new MessageLogin("KO", "/home");
	}

	// check email exist
	@GetMapping("/checkemail/{email}")
	public Message checkEmail(@PathVariable String email) {
		return new Message(accountService.checkEmail(email) ? "KO" : "OK");
	}

	// check user name exist
	@GetMapping("/checkusername/{username}")
	public Message checkUsername(@PathVariable String username) {
		return new Message(accountService.checkUsername(username) ? "KO" : "OK");
	}

	// refresh token to keep login
	@GetMapping("/refreshtoken/{token}")
	public Message refreshToken(@PathVariable String token) {
		return new Message(jwtService.refreshToken(token));
	}

	// get name user from token
	@GetMapping("/getname/{token}")
	public Message getName(@PathVariable String token) {
		if (jwtService.validateTokenLogin(token)) {
			String username = jwtService.getUsernameFromToken(token);
			String name = accountService.getName(username);
			return new Message(name.equals("NULL") ? "KO" : name);
		}
		return new Message("KO");
	}

	// get redirect url when user has logged and try access to login
	@GetMapping("/redirect/{token}")
	public Message redirect(@PathVariable String token) {
		if (jwtService.validateTokenLogin(token)) {
			String username = jwtService.getUsernameFromToken(token);
			User u = accountService.login(username);
			if (u != null) {
				String url = "/home";
				switch (u.getRole().getId()) {
				case 1:
					url = "/home";
					break;
				case 2:
					url = "/staff";
					break;
				case 3:
					url = "/admin";
					break;
				default:
					url = "/home";
				}
				return new Message(url);
			}
		}
		return new Message("KO");
	}
	
	//get user from token
	@GetMapping("/getuserfromtoken/{token}")
	public User getUserFromToken(@PathVariable String token) {
		if (jwtService.validateTokenLogin(token)) {
			String username = jwtService.getUsernameFromToken(token);
			return accountService.getUserByUsername(username);
		}
		return null;
	}

	//update profile
	@PostMapping("/updateprofile")
	public Message updateProfile(@RequestBody User user) {
		return accountService.updateProfile(user) ? new Message("OK") : new Message("KO");
	}

	//check old password when change password
	@PostMapping("/checkoldpass/{token}")
	public Message checkOldPass(@RequestBody String pass, @PathVariable String token) {
		if (jwtService.validateTokenLogin(token)) {
			String username = jwtService.getUsernameFromToken(token);
			User u = accountService.getUserByUsername(username);
			if (u != null) {
				if (encrypt.matches(pass, u.getPassword())) {
					return new Message("OK");
				} else {
					return new Message("KO");
				}
			}
		}
		return new Message("KO");
	}

	//change password
	@PostMapping("/updatepass/{token}")
	public Message updatePass(@RequestBody String newpass, @PathVariable String token) {
		if (jwtService.validateTokenLogin(token)) {
			String username = jwtService.getUsernameFromToken(token);
			User u = accountService.getUserByUsername(username);
			u.setPassword(newpass);

			return accountService.updateProfile(u) ? new Message("OK") : new Message("KO");
		}
		return new Message("KO");
	}

	//check token valid or invalid
	@GetMapping("/checktoken/{token}")
	public Message checkTokenExpire(@PathVariable String token) {
		try {
			if (jwtService.validateTokenLogin(token)) {
				return new Message("OK");
			}
		} catch (Exception e) {
			return new Message("KO");
		}
		return new Message("KO");
	}
	
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/getabc")
	public List<?> getabc(){
		return userRepository.totalUser();
	}
}
