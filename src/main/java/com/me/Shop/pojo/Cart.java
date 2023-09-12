package com.me.Shop.pojo;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Cart {
	
	public Cart()
	{
		
	}
	private ProductInfo product;
	private int quantity;
	
	@Override
	public String toString() {
		return "Cart [Product=" + product + ", Quantity=" + quantity + "]";
	}
	public ProductInfo getProduct() {
		return product;
	}
	public void setProduct(ProductInfo product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
