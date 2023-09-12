package com.me.Shop.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.me.Shop.Dao.ProductDao;


public class TestDaoMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductDao prod = new ProductDao();
		System.out.println(prod.countQuery());
	}

}
