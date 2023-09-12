package com.me.Shop.Dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.me.Shop.pojo.CustomerAddress;
import com.me.Shop.pojo.User;

@Component
public class AddressDao extends Dao {
	
public AddressDao()
{
	
}

public void save(CustomerAddress address) {
	begin();
	int t = (int) getSession().save(address);
	System.out.println(t);
	commit();
}
public List<CustomerAddress> AddressList(int userid) {
    
	List<CustomerAddress> addressess = null;
    try {
    	System.out.println("From customeraddresses where username = "+userid);
    	Query query = getSession().createQuery("From CustomerAddress where AddressCustomerID = "+userid);
//        query.setFirstResult(firstResult);
//        query.setMaxResults(maxResults);
    	
    	addressess = (List<CustomerAddress>) query.list();
        if (addressess != null) {
            for (CustomerAddress address : addressess) {
                System.out.println("Retrieved addresses" + address.getStreetName());
            }
        }
    } catch (HibernateException e) {
        e.printStackTrace();
    } 
   return addressess;
}


public CustomerAddress getaddress(int id) {
	System.out.println("FROM CustomerAddress where id = "+id);
	Query query = getSession().createQuery("FROM CustomerAddress where id = "+id);
	query.setMaxResults(1);
	CustomerAddress address = (CustomerAddress) query.uniqueResult();
	return address; 
}
}
