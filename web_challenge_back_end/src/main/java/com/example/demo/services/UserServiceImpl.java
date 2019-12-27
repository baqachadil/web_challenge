package com.example.demo.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entities.AppRole;
import com.example.demo.Entities.AppUser;
import com.example.demo.Entities.Shop;
import com.example.demo.Entities.UserShop;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.ShopRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserShopRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private UserShopRepository userShopRepository;
	
	@Override
	public AppUser AddUSer(AppUser user) {		
		//We crypt the user's password
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));		
		return userRepository.save(user);		
	}

	@Override
	public AppUser findUserByUserNamme(String username) {		
		return userRepository.findByUsername(username);
	}

	@Override
	public AppRole AddRole(AppRole role) {
		roleRepository.save(role);
		return null;
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		AppRole role = roleRepository.findByName(rolename);
		AppUser user = userRepository.findByUsername(username);
		user.getRoles().add(role);
	}

	@Override
	public void likeShop(String username, Long shop_id) {
		AppUser user = userRepository.findByUsername(username);
		Optional<Shop> shop = shopRepository.findById(shop_id);
		user.getuserShops().add(new UserShop(1, new Date(), user, shop.get()));
		userRepository.save(user);
		
	}

	@Override
	public void dislikeShop(String username, Long shop_id) {
		AppUser user = userRepository.findByUsername(username);
		Optional<Shop> shop = shopRepository.findById(shop_id);
		user.getuserShops().add(new UserShop(0, new Date(), user, shop.get()));
		userRepository.save(user);
		
	}

	@Override
	public void deletePreferredShop(String username, Long shop_id) {
		AppUser user = userRepository.findByUsername(username);		
		userShopRepository.deleteUserShop(shop_id, user.getId());
	}

}
