package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PostMapping("/likeShop/{shop_id}")
	public void likeShop(@PathVariable Long shop_id) {
		//Getting the authenticated user from springSecurity context
		AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();		
		String username=auth.getName().toString();
		userservice.likeShop(username, shop_id);
	}
	
	@PostMapping("/disklikeShop/{shop_id}")
	public void dislikeShop(@PathVariable Long shop_id) {
		//Getting the authenticated user from springSecurity context
		AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();		
		String username=auth.getName().toString();
		userservice.dislikeShop(username, shop_id);
	}
	
	@DeleteMapping("/delete/{shop_id}")
	public void deletePreferredShop(@PathVariable Long shop_id) {
		//Getting the authenticated user from springSecurity context
		AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();		
		String username=auth.getName().toString();
		userservice.deletePreferredShop(username, shop_id);
	}
}
