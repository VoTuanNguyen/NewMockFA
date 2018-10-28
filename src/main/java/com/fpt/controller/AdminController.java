package com.fpt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.entity.Role;
import com.fpt.entity.User;
import com.fpt.model.AdminChart;
import com.fpt.model.Message;
import com.fpt.service.AdminService;
import com.fpt.service.RoleService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/access")
	public Message checkAccess() {
		return new Message("Access");
	}
	
	@RequestMapping("/getalluser")
	public List<User> getAllUser(){
		return adminService.findAll();
	}
	
	@PutMapping("/update")
	public Message update(@RequestBody User user) {
		if(adminService.updateUser(user) != null) {
			return new Message("OK");
		}
		return new Message("KO");
	}
	
	@GetMapping("/getrole")
	public List<Role> getRole(){
		return roleService.findAllRole();
	}
	
	@GetMapping("/getchart")
	public AdminChart getChart() {
		return adminService.getChart();
	}
}
