package com.fpt.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.User;
import com.fpt.model.LoginModel;
import com.fpt.repository.UserRepository;
import com.fpt.service.AccountService;

@Service
public class AccountServiceImp implements AccountService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User login(String username) {
		User u = null;
		u = userRepository.findUserByUserName(username);
		return u != null ? u : null;
	}

	@Override
	public LoginModel findUserByUserName(String username) {
		User u = userRepository.findUserByUserName(username);
		LoginModel loginModel = new LoginModel(u.getUsername(), u.getPassword());
		loginModel.setRoles(new String[] { u.getRole().getName() });
		return loginModel;
	}

	@Override
	public User register(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public boolean checkEmail(String email) {
		User u = null;
		u = userRepository.findUserByEmail(email);
		return u != null;
	}

	@Override
	public boolean checkUsername(String username) {
		User u = null;
		u = userRepository.findUserByUserName(username);
		return u != null;
	}

	@Override
	public String getName(String username) {
		User u = userRepository.findUserByUserName(username);
		if(u != null) {
			return u.getName();
		}
		return "NULL";
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findUserByUserName(username);
	}

	@Override
	public boolean updateProfile(User user) {
		User us = null;
		us = userRepository.saveAndFlush(user);
		
		return us == null ? false : true;
	}

}
