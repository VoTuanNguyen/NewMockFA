package com.fpt.service;

import com.fpt.entity.User;
import com.fpt.model.LoginModel;

public interface AccountService {
	LoginModel findUserByUserName(String username);
	LoginModel login(String username);
	User register(User user);
}
