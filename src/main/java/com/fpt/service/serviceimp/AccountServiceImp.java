package com.fpt.service.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.User;
import com.fpt.model.LoginModel;
import com.fpt.repository.AccountRepository;
import com.fpt.service.AccountService;

@Service
public class AccountServiceImp implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public LoginModel login(String username) {
		User u = null;
		u = accountRepository.findUserByUserName(username);
		LoginModel loginModel = null;
		if (u != null) {
			loginModel = new LoginModel(u.getUsername(), u.getPassword());
			loginModel.setRoles(new String[] { u.getRole().getName() });
		}
		return loginModel;
	}

	@Override
	public LoginModel findUserByUserName(String username) {
		User u = accountRepository.findUserByUserName(username);
		LoginModel loginModel = new LoginModel(u.getUsername(), u.getPassword());
		loginModel.setRoles(new String[] { u.getRole().getName() });
		return loginModel;
	}

	@Override
	public User register(User user) {
		return accountRepository.saveAndFlush(user);
	}

}
