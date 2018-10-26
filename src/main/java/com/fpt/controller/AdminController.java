package com.fpt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.entity.User;
import com.fpt.model.Message;
import com.fpt.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/access")
	public Message checkAccess() {
		return new Message("Access");
	}
	
	@RequestMapping("/getalluser")
	public List<User> getAllUser(){
		return adminService.findAll();
	}
	
	@PutMapping("/update")
	public String update(@RequestBody User user) {
		if(adminService.updateUser(user) != null) {
			return "OK";
		}
		return "KO";
	}
}
