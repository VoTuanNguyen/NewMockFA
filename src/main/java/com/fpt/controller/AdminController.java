package com.fpt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.entity.User;
import com.fpt.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/access")
	public String checkAccess() {
		return "Access";
	}
	
	@RequestMapping("/getalluser")
	public List<User> getAllUser(){
		return adminService.findAll();
	}
	
	@PutMapping("/update")
	public String update(@RequestBody User user) {
		System.out.println(user);
		if(adminService.updateUser(user) != null) {
			return "OK";
		}
		return "KO";
	}
}
