package com.tausif.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Product {
	
	@Id
	private String name;
	private String cName;
	private int price;
	private String description;
	
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
	@Override
	public String toString() {
		return "Product [name=" + name + ", cName=" + cName + ", price=" + price + ", description=" + description + "]";
	}
	
	

}
