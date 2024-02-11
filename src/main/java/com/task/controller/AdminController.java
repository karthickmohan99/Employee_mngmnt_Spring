package com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.AdminAuth;
import com.task.service.AdminAuthService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	AdminAuthService adminService;
	
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody AdminAuth admin) {
		return adminService.register(admin);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody AdminAuth admin) {
		return adminService.login(admin);
		
	}
}
