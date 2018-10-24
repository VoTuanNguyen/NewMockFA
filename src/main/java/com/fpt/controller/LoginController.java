package com.fpt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class LoginController {
	
	@RequestMapping("/")
	public String demo() {
		return "OK";
	}
}
