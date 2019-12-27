package com.example.demo.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name="User")
public class AppUser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	@Column(unique=true)
	private String username;	
	private String password;
	private double latitude;
	private double longitude;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<AppRole> roles = new ArrayList<AppRole>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserShop> userShops;		
	
	public AppUser() {
		super();
	}

	public AppUser(String username, String password, double latitude, double longitude, List<UserShop> userShops) {
		super();
		this.username = username;
		this.password = password;
		this.latitude = latitude;
		this.longitude = longitude;
		this.userShops = userShops;
	}

	

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public List<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AppRole> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	//we should not return the password to the front-end even if it is crypted for security reasons
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonSetter
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

	public List<UserShop> getuserShops() {
		return userShops;
	}

	public void setuserShops(List<UserShop> userShops) {
		this.userShops = userShops;
	}

	
	
	
}
