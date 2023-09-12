package com.me.Shop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.me.Shop.pojo.Cart;
import com.me.Shop.pojo.Order;
import com.me.Shop.pojo.ProductInfo;
import com.me.Shop.pojo.OrderLine;
@Component
public class OrderDao extends Dao {
	public OrderDao()
	{
		
	}
	public void save(Order order,ArrayList<Cart> cartt) {
		begin();
		int id = (int) getSession().save("order", order);
		commit();
		//getSession().save(order);
//		int id = order.getUser().getId();
		
		for(int i=0;i<cartt.size();i++)
		{
			OrderLine orderline = new OrderLine();
			Cart cart = cartt.get(i);
			orderline.setProduct(cart.getProduct());
			orderline.setPrice(cart.getProduct().getPrice());
			orderline.setProductname(cart.getProduct().getProductName());
			orderline.setQuantity(cart.getQuantity());
			orderline.setOrder(order);
			begin();
			int lineid = (int) getSession().save("orderline",orderline);
			commit();
			System.out.println(lineid);
		}
		
	}
	
	public List<Order> OrderList(int id) {
        
		List<Order> orderslist = null;
        try {
        	
        	Query query = getSession().createQuery("From Order where userID= "+id);
            orderslist = (List<Order>) query.list();
            if (orderslist != null) {
                for (Order order : orderslist) {
                    System.out.println("Retrieved Products" + order.getPrice());
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } 
       return orderslist;
    }
	
	public List<OrderLine> Orderlineitems(int id)
	{
		List<OrderLine> orderslist = null;  
           	Query query = getSession().createQuery("From OrderLine where orderid= "+id);
            orderslist = (List<OrderLine>) query.list();
            if (orderslist != null) {
                for (OrderLine line : orderslist) {
                    System.out.println("Retrieved Products" + line);
                }
            }
            return orderslist;

	}
	public float getTotalPrice(int id)
	{	Query query = getSession().createQuery("select price From Order where orderid= "+id);
	    query.setMaxResults(1);
		float i =0;
		i = (float) query.uniqueResult();
		return i;
	}

}
