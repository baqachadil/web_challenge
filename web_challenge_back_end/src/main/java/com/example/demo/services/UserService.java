package com.example.demo.services;

import com.example.demo.Entities.AppRole;
import com.example.demo.Entities.AppUser;

public interface UserService {
	public AppUser AddUSer(AppUser user);
	public AppUser findUserByUserNamme(String username);
	public AppRole AddRole(AppRole role);
	public void addRoleToUser(String username, String rolename);
	public void likeShop(String username, Long shop_id);
	public void dislikeShop(String username, Long shop_id);
	public void deletePreferredShop(String username, Long shop_id);
}
