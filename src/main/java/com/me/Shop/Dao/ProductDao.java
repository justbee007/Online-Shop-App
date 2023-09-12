package com.me.Shop.Dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
//import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import com.me.Shop.pojo.ProductInfo;
import com.me.Shop.pojo.User;

@Component
public class ProductDao extends Dao {
	public ProductDao()
	{
		
	}
	public void save(ProductInfo product) {
		begin();
		getSession().save(product);
		commit();
	}
	public List<ProductInfo> ProductList(int firstResult, int maxResults) {
        
		List<ProductInfo> products = null;
        try {
        	
        	Query query = getSession().createQuery("From ProductInfo");
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResults);
            products = (List<ProductInfo>) query.list();
            if (products != null) {
                for (ProductInfo product : products) {
                    System.out.println("Retrieved Products" + product.getProductName());
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } 
       return products;
    }

	public Long countQuery()
	{
        int productCount = 0;
        Query query = getSession().createQuery("Select count (*) From ProductInfo");
            Long products = (Long) query.uniqueResult();
            return products;
    }
	public ProductInfo getproductinfo(String id) {
		System.out.println("FROM ProductInfo where id ="+id );
		Query query = getSession().createQuery("FROM ProductInfo where id = '"+id +"'");
		query.setMaxResults(1);
		ProductInfo product = (ProductInfo) query.uniqueResult();
		return product; 
	}
	public boolean deleteproductinfo(String id)
	{
		try
		{
		ProductInfo product = new ProductInfo();
		product.setId(Integer.parseInt(id));
		begin();
		getSession().delete(product);
		commit();
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean updateproduct(ProductInfo product)
	{
		try {
			begin();
			if(product.getImgfile().getSize()==0)
			{
				System.out.println("update ProductInfo set productname= '"+product.getProductName() +"',"+"productdesc= '"+product.getProductDesc() +"',"+"price='"+product.getPrice() +"',userid=" +product.getUser().getId()+" where id ="+product.getId());
	        Query query = getSession().createQuery("update ProductInfo set productname= '"+product.getProductName() +"',"+"productdesc= '"+product.getProductDesc() +"',"+"price='"+product.getPrice() +"',userid=" +product.getUser().getId()+" where id ="+product.getId());
            int products = query.executeUpdate();
            commit();
			}
            else
            {		System.out.println("update ProductInfo set productname= '"+product.getProductName() +"',"+"productdesc= '"+product.getProductDesc() +"',"+"imgname= '"+product.getImgname() +"',"+"price='"+product.getPrice()+ "',userid= " +product.getUser().getId()+ " where id ="+product.getId());
            	   Query query = getSession().createQuery("update ProductInfo set productname= '"+product.getProductName() +"',"+"productdesc= '"+product.getProductDesc() +"',"+"imgname= '"+product.getImgname() +"',"+"price='"+product.getPrice()+ "',userid= " +product.getUser().getId()+ " where id ="+product.getId());
                   int products = query.executeUpdate();
                   System.out.println(products);
                   commit();  
            }
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
		return false;
		}
	}
}
