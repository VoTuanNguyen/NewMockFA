package com.fpt.service.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.User;
import com.fpt.model.AdminChart;
import com.fpt.repository.UserRepository;
import com.fpt.service.AdminService;

@Service
public class AdminServiceImp implements AdminService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public AdminChart getChart() {
		List<User> lstUser = userRepository.findAll();

		AdminChart adminChart = new AdminChart();
		int admin = 0, staff = 0, user = 0;
		for (User u : lstUser) {
			switch (u.getRole().getId()) {
			case 1:
				user++;
				break;
			case 2:
				staff++;
				break;
			case 3:
				admin++;
				break;
			}
		}
		adminChart = new AdminChart(lstUser.size(), admin, staff, user);

		return adminChart;
	}

}
