package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.Entities.AppRole;
import com.example.demo.Entities.AppUser;
import com.example.demo.Entities.Shop;
import com.example.demo.repositories.ShopRepository;
import com.example.demo.services.UserServiceImpl;

@SpringBootApplication
public class WebChallengeBackEndApplication implements CommandLineRunner {

	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private UserServiceImpl userService;;
	
	public static void main(String[] args) {
		SpringApplication.run(WebChallengeBackEndApplication.class, args);
	}
	
	
	//return an instance of bcrypte because spring boot does not do it automatically
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		userService.AddRole(new AppRole(null,"ADMIN"));
		userService.AddRole(new AppRole(null,"USER"));
				
		userService.AddUSer(new AppUser("admin@gmail.com","Admin1234",12,13,null));
		userService.AddUSer(new AppUser("baqachadil@gmail.com","Adil1234",12,13,null));				
		
		userService.addRoleToUser("admin@gmail.com", "ADMIN");
		userService.addRoleToUser("admin@gmail.com", "USER");
		
		userService.addRoleToUser("baqachadil@gmail.com", "USER");
		
		shopRepository.save(new Shop("shop1","img1",null,null,null,13.23,7.515,null));
		shopRepository.save(new Shop("shop2","img2",null,null,null,13.23,7.515,null));
		shopRepository.save(new Shop("shop3","img3",null,null,null,13.23,7.515,null));
		shopRepository.save(new Shop("shop4","img4",null,null,null,13.23,7.515,null));
	}

}
