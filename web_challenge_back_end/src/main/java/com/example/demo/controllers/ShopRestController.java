package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Entities.Shop;
import java.util.List;
import com.example.demo.repositories.ShopRepository;
import com.example.demo.services.ShopService;

@RestController
public class ShopRestController {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private ShopService shopService;
	
	@GetMapping("/shops")
	public List<Shop> getShops(){
		AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();		
		String username=auth.getName().toString();				
		return shopService.getShops(username);
	}
	
	@PostMapping("/shops")
	public Shop addShop(@RequestBody Shop shop) {
		return shopRepository.save(shop);
	}
		
}
