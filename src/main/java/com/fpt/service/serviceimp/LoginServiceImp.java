package com.fpt.service.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.User;
import com.fpt.model.LoginModel;
import com.fpt.repository.LoginRepository;
import com.fpt.service.LoginService;

@Service
public class LoginServiceImp implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public LoginModel findUserByUserName(String userName) {
		User u = loginRepository.findUserByUserName(userName);
		LoginModel loginModel = new LoginModel(u.getUserName(), u.getPassword());
		loginModel.setRoles(new String[] { u.getRole().getName() });
		return loginModel;
	}

}
