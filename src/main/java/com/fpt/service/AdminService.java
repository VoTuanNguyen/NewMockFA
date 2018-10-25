package com.fpt.service;

import java.util.List;

import com.fpt.entity.User;

public interface AdminService {
	List<User> findAll();
	User updateUser(User user);
}
