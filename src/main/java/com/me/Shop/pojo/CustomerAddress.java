package com.me.Shop.pojo;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="customeraddresses")
public class CustomerAddress {
@Id
@Basic
@GeneratedValue(strategy = GenerationType.IDENTITY)	
 private int id;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getStreetName() {
	return streetName;
}
public void setStreetName(String streetName) {
	this.streetName = streetName;
}
public String getApartmentNumber() {
	return apartmentNumber;
}
public void setApartmentNumber(String apartmentNumber) {
	this.apartmentNumber = apartmentNumber;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public int getZipCode() {
	return zipCode;
}
public void setZipCode(int zipCode) {
	this.zipCode = zipCode;
}
private String streetName;
 private String apartmentNumber;
 private String city;
 private String state;
 private int zipCode;
@OneToOne
@JoinColumn(name = "AddressCustomerID")
 private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
 
}
