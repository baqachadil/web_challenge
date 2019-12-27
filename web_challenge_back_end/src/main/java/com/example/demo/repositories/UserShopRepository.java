package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entities.Shop;
import com.example.demo.Entities.UserShop;

public interface UserShopRepository extends JpaRepository<UserShop, Long> {
	@Transactional
	@Modifying
	@Query("delete from UserShop s where s.shop.id=?1 and s.user.id=?2")
	public void deleteUserShop(Long shop_id, Long user_id);
	
	@Query("select s.shop from UserShop s where s.user.id=?1")
	public List<Shop> findUserShops(Long user_id);
	
	@Query("select s.shop from UserShop s where s.user.id=?1 and s.liked=1")
	public List<Shop> findPreferredShops(Long user_id);
}
