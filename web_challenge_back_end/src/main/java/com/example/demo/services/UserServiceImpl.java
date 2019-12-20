package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entities.AppRole;
import com.example.demo.Entities.AppUser;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
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

}
