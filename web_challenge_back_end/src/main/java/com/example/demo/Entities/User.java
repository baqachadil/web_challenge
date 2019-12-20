package com.example.demo.Entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="User")
public class User implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	@Column(unique=true)
	private String username;
	private String password;
	private double latitude;
	private double longitude;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserShop> likes;		
	
	public User() {
		super();
	}

	public User(String username, String password, double latitude, double longitude, Set<UserShop> likes) {
		super();
		this.username = username;
		this.password = password;
		this.latitude = latitude;
		this.longitude = longitude;
		this.likes = likes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Set<UserShop> getUsers() {
		return likes;
	}

	public void setUsers(Set<UserShop> likes) {
		this.likes = likes;
	}
	
	
}
