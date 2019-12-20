package com.example.demo.Entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Shop")
public class Shop implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String name;
	private String image;
	private String phone;
	private String address;
	private String description;
	private double latitude;
	private double longitude;
	
	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
	private Set<UserShop> likes;
	
	public Shop() {
		super();
	}

	public Shop(String name, String image, double latitude, double longitude, Set<UserShop> likes) {
		super();
		this.name = name;
		this.image = image;
		this.latitude = latitude;
		this.longitude = longitude;
		this.likes = likes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public Set<UserShop> getLikes() {
		return likes;
	}

	public void setLikes(Set<UserShop> likes) {
		this.likes = likes;
	}
	
	
	
}
