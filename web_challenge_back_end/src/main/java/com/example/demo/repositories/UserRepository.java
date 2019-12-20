package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findByUsername(String username);
}
