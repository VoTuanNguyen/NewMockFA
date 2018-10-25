package com.fpt.service.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.User;
import com.fpt.repository.AdminRepository;
import com.fpt.service.AdminService;

@Service
public class AdminServiceImp  implements AdminService{
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public List<User> findAll() {
		return adminRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		return adminRepository.saveAndFlush(user);
	}
	
	
}
