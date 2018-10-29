package com.fpt.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.Role;
import com.fpt.repository.RoleRepository;
import com.fpt.service.RoleService;

@Service
public class RoleServiceImp implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> findAllRole() {
		return roleRepository.findAll();
	}

}
