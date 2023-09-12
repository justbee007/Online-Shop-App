package com.me.Shop.cartBuilder;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.me.Shop.pojo.Cart;
import com.me.Shop.pojo.ProductInfo;
import com.me.Shop.pojo.User;
@Component
public class CartBuilder {
//@Autowired
//private RedisTemplate<String, Object> reduisTemplate;
	public ArrayList<Cart> addtocart(String[] id, String[] name,String[]productdesc,String[] price,String[] quantity,String [] imgname,HttpSession session)
	{ 
//		HttpSession session;
		ArrayList<Cart> cart = new ArrayList<Cart>();
		 System.out.println(session.getAttribute("cart"));
		 if(session.getAttribute("cart")==null)
		 {
		 
		 for(int i=0;i<id.length;i++)
		 {
			 if(quantity[i].isEmpty())
			 {
				 
			 }
			 else
			 {
				 ProductInfo prod = new ProductInfo();
				 prod.setId(Integer.parseInt(id[i]));
				 prod.setPrice(Float.parseFloat(price[i]));
				 prod.setProductDesc(productdesc[i]);
				 prod.setUser((User) session.getAttribute("user"));
				 prod.setImgname(imgname[i]);
				 prod.setProductName(name[i]);
				 Cart cartt = new Cart();
				 cartt.setProduct(prod);
				 cartt.setQuantity(Integer.parseInt(quantity[i]));
				 cart.add(cartt);
				 	 
			 }
			 
		 }
		 }
		 else
		 {
			 cart = (ArrayList<Cart>) session.getAttribute("cart");
			 for(int i=0;i<id.length;i++)
			 {
				 if(quantity[i].isEmpty())
				 {
					 
				 }
				 else
				 {	
					boolean flag = exists(cart,Integer.parseInt(id[i]));
					if(flag== true)
					{
						int position = findposition(cart,Integer.parseInt(id[i]));
						cart.get(position).setQuantity(cart.get(position).getQuantity()+Integer.parseInt(quantity[i]));
						
					}
					else
					{
						 ProductInfo prod = new ProductInfo();
						 prod.setId(Integer.parseInt(id[i]));
						 prod.setPrice(Float.parseFloat(price[i]));
						 prod.setProductDesc(productdesc[i]);
						 prod.setUser((User) session.getAttribute("user"));
						 prod.setImgname(imgname[i]);
						 prod.setProductName(name[i]);
						 Cart cartt = new Cart();
						 cartt.setProduct(prod);
						 cartt.setQuantity(Integer.parseInt(quantity[i]));
						 cart.add(cartt);

//						RedisTemplate<String, Object> reduisTemplate =(RedisTemplate) context.getBean("reis");
//						reduisTemplate.opsForList().rightPush((String) session.getAttribute("user"),cart);
					}
					 
				 }
				 
		  }
			 
			 
		 }
      if(session.getAttribute("cart") == null)
      {
    	  session.setAttribute("cart", cart);
      }
      
      else
      { 
    	  ArrayList<Cart> cartt = (ArrayList<Cart>) session.getAttribute("cart");
    	  System.out.println(session.getAttribute("cart"));
      } 
		 return cart;
	}
	public float totalPrice(ArrayList<Cart> cart)
	{
		float totalPrice =0;
		if(cart == null)
		{
			
		}
		else
		{
		for(int i=0;i<cart.size();i++)
		{ float price = cart.get(i).getProduct().getPrice();
			totalPrice = totalPrice+price*(cart.get(i).getQuantity());
			DecimalFormat decimal = new DecimalFormat("#.##");
			String temp = decimal.format(totalPrice);
			totalPrice = Float.parseFloat(temp);
		}
		}
		return totalPrice;
	}
	
	public boolean exists(ArrayList<Cart> cartt,int id)
	{
		boolean existFlag = false;
		for(int i=0;i<cartt.size();i++)
		{
			if(cartt.get(i).getProduct().getId() ==id)
			{
				existFlag = true;
				return existFlag;
			}
		}
		return existFlag;
		
	}
	public int findposition(ArrayList<Cart> cartt,int id)
	{ int position=-1;
		for(int i=0;i<cartt.size();i++)
		{
			if(cartt.get(i).getProduct().getId() ==id)
			{
			 position = i;
				
			}
			
		}
		return position;
	}
	
	
}
