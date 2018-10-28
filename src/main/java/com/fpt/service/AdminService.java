package com.fpt.service;

import java.util.List;

import com.fpt.entity.User;
import com.fpt.model.AdminChart;

public interface AdminService {
	List<User> findAll();
	User updateUser(User user);
	AdminChart getChart();
}
