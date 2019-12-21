package com.example.demo.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class UserShop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private int liked;
	private Date like_date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AppUser user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Shop shop;

	public UserShop() {
		super();
	}

	public UserShop(int liked, Date liked_date, AppUser user, Shop shop) {
		super();
		this.liked = liked;
		this.like_date = liked_date;
		this.user = user;
		this.shop = shop;
	}

	public int getliked() {
		return liked;
	}

	public void setliked(int liked) {
		this.liked = liked;
	}

	public Date getliked_date() {
		return like_date;
	}

	public void setliked_date(Date like_date) {
		this.like_date = like_date;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	
	
	
}
