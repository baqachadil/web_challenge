package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.AppUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userService.findUserByUserNamme(username);
		if(user==null) throw new UsernameNotFoundException(username);
		
		//we only need users with the role USER in this application
		Collection<GrantedAuthority> auts=new ArrayList<GrantedAuthority>();
		user.getRoles().forEach(r->{
			auts.add(new SimpleGrantedAuthority(r.getRole()));
		});
		
		return new User(user.getUsername(), user.getPassword(), auts);
	}

}
