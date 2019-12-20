package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entities.AppUser;
import com.example.demo.services.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userservice;
	
	@PostMapping("/register")
	public AppUser register(@RequestBody AppUser user) {
		AppUser test = userservice.findUserByUserNamme(user.getUsername());
		if(test!=null) throw new RuntimeException("User already exists !!");
		else return userservice.AddUSer(user);
	}
}
