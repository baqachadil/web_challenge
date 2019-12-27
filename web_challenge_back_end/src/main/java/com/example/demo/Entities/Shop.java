package com.example.demo.Entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Shop")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shop implements Serializable, Comparable<Shop> {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String name;
	private String image;
	private String phone;
	private String address;
	private String description;
	private double latitude;
	private double longitude;
	private double distance;
	
	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<UserShop> likes;
	
	public Shop() {
		super();
	}

	

	public Shop(String name, String image, String phone, String address, String description, double latitude,
			double longitude, Set<UserShop> likes) {
		super();
		this.name = name;
		this.image = image;
		this.phone = phone;
		this.address = address;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.likes = likes;
	}

	

	public Long getId() {
		return Id;
	}



	public void setId(Long id) {
		Id = id;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
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



	public double getDistance() {
		return distance;
	}



	public void setDistance(double distance) {
		this.distance = distance;
	}


	//for sorting
	@Override
	public int compareTo(Shop shop) {
		return (this.getDistance() < shop.getDistance() ? -1 : 
            (this.getDistance() == shop.getDistance() ? 0 : 1));     
	}
	
	
	
}
