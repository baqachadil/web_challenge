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
		
		shopRepository.save(new Shop("shop1","https://www.whitecatjoinery.co.uk/wp-content/uploads/2012/10/shop-front-4.jpg","(+212) 65234253","N°09 Street Al Ahlam RABAT","This shop is specialiezd in selling clothes",31.6344,-8.05867,null));
		shopRepository.save(new Shop("shop2","https://advshopfront.co.uk/wp-content/uploads/5.-Toughenglass-Shopfront-1.jpg","(+212) 76545432","N°199 Street Enour RABAT","Good shop for shoes and hats ",31.2384,-7.65867,null));
		shopRepository.save(new Shop("shop3","https://jooinn.com/images/shop-fronts-5.jpg","(+212) 56413245","N°87 Street Ward and Enour RABAT","Our shop is the best in it's field, thanks for joining us",31.6543394,-8.0077221,null));
		shopRepository.save(new Shop("shop4","https://www.huxleyandco.co.uk/wp-content/uploads/2018/11/timber-shop-fronts_2.jpg","(+212) 65433412","N°03 Street ELBAHJA RABAT","You would love our shop! trust me :)",31.1244,-8.61437,null));
	}

}
