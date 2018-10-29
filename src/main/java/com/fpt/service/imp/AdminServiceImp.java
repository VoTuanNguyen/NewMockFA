package com.fpt.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Page<User> getUserPage(int role, String keySearch, int creator, Pageable pageable) {
		
		// filter role
		if (keySearch.equals("%-1%") && creator == -1 && role != -1) {
			return userRepository.getUserByRole(role, pageable);
		}

		// search user name
		if (role == -1 && creator == -1 && !keySearch.equals("%-1%")) {
			return userRepository.getUserByUsername(keySearch, pageable);
		}

		// filter creator
		if (keySearch.equals("%-1%") && role == -1 && creator != -1) {
			return userRepository.getUserByCreator(creator, pageable);
		}

		// filter role and creator
		if (keySearch.equals("%-1%") && role != -1 && creator != -1) {
			return userRepository.getUserByRoleCreator(role, creator, pageable);
		}

		// filter role and search user name
		if (creator == -1 && role != -1 && !keySearch.equals("%-1%")) {
			return userRepository.getUserByRoleSearch(role, keySearch, pageable);
		}

		// filter creator and search user name
		if (role == -1 && creator != -1 && !keySearch.equals("%-1%")) {
			return userRepository.getUserByCreatorSearch(creator, keySearch, pageable);
		}

		// filter role, creator and search user name
		if (role != -1 && creator != -1 && !keySearch.equals("%-1%")) {
			return userRepository.getUserByRoleCreatorSearch(role, creator, keySearch, pageable);
		}

		// default role = -1 creator = -1 and keySearch = "%-1%"
		return userRepository.findAll(pageable);
	}

}
