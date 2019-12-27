package com.example.demo.services;

import java.util.List;

import com.example.demo.Entities.Shop;

public interface ShopService {
	public List<Shop> getShops(String username);
	public Shop AddShop(Shop shop);
	public List<Shop> getPreferredShops(String username);
}
