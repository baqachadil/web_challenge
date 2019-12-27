package com.example.demo.services;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entities.AppUser;
import com.example.demo.Entities.Shop;
import com.example.demo.Entities.UserShop;
import com.example.demo.repositories.ShopRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserShopRepository;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserShopRepository usershopRepository;
		
	
	@Override
	public List<Shop> getShops(String username) {
		
		AppUser user = userRepository.findByUsername(username);		
		
		
		List<UserShop> userLikedShops = usershopRepository.findAll();
		//Checking if the disliked shops has passed 2h
		Date d = new Date();	
		userLikedShops.forEach(us->{
			if(us.getliked()==0) {
				double passed_hours = (double) ((d.getTime() - us.getliked_date().getTime()) / 1000)/3600;
				if(passed_hours > 2) usershopRepository.delete(us);
			}
		});
		
				
		double x=user.getLatitude();
		double y=user.getLongitude();
		
		
		//allShops
		List<Shop> shops = shopRepository.findAll();
		//Shops liked/disliked with the user
		List<Shop> userShops = usershopRepository.findUserShops(user.getId());
		List<Shop> ShopsToShowInHome = new ArrayList<Shop>();		
		
		for(Shop s:shops) {
			
			//calculating the distance from the user
			double distance=Math.sqrt(Math.pow(x-s.getLatitude(), 2)+Math.pow(y-s.getLongitude(), 2));
			s.setDistance(distance);
			
			//removing the liked and disliked shops from the home page			
			int out_cdt = 0;						
			for(Shop us:userShops) {				
				if(us == s) {
					out_cdt=1;
					break;
				}				
			}
			if(out_cdt==0)ShopsToShowInHome.add(s);					
		}
		
		//sorting by the nearest
		Collections.sort(ShopsToShowInHome);
		
		return ShopsToShowInHome;
	}
	
	public List<Shop> getPreferredShops(String username){
		AppUser user = userRepository.findByUsername(username);
		return usershopRepository.findPreferredShops(user.getId());
	}

	@Override
	public Shop AddShop(Shop shop) {
		return shopRepository.save(shop);
	}

}
