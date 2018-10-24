package com.fpt.service;

import com.fpt.model.LoginModel;

public interface LoginService {
	LoginModel findUserByUserName(String username);
}
