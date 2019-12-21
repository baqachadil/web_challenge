package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
	
}
