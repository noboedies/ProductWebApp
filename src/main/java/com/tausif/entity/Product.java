package com.tausif.entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Product {
	
	@Id
	private String name;
	private String cName;
	private int price;
	private String description;
	@Column(nullable = true, columnDefinition = "longblob")
	private byte[] image;
	@Column(nullable = true, columnDefinition = "longblob")
	private byte[] content;
	
	@ManyToOne
	private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", cName=" + cName + ", price=" + price + ", description=" + description
				+ ", image=" + Arrays.toString(image) + ", content=" + Arrays.toString(content) + ", user=" + user
				+ "]";
	}
	
	
	
	
}
