package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Entities.Shop;
import java.util.List;
import com.example.demo.repositories.ShopRepository;

@RestController
public class ShopRestController {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@GetMapping("/shops")
	public List<Shop> getShops(){
		return shopRepository.findAll();
	}
	
	@PostMapping("/shops")
	public Shop addShop(@RequestBody Shop shop) {
		return shopRepository.save(shop);
	}
		
}
