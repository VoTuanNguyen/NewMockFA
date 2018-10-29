package com.fpt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fpt.entity.User;
import com.fpt.model.AdminChart;

public interface AdminService {
	List<User> findAll();

	User updateUser(User user);

	AdminChart getChart();

	Page<User> getUserPage(int role, String keySearch, int creator, Pageable pageable);
}
