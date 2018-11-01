package com.fpt.service;

import com.fpt.entity.User;
import com.fpt.model.LoginModel;

public interface AccountService {
	LoginModel findUserByUserName(String username);
	User login(String username);
	User register(User user);
	boolean checkEmail(String email);
	boolean checkUsername(String username);
	String getName(String username);
	User getUserByUsername(String username);
	boolean updateProfile(User user);
}
