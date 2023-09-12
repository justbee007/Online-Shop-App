package com.me.Shop.Dao;
import java.util.List;
import com.me.Shop.Dao.Dao;
import com.me.Shop.pojo.User;
import org.hibernate.HibernateException;
//import org.hibernate.query.Query;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

@Component
public class UserDao extends Dao {
	public UserDao()
	{
		
	}
public void save(User user) {
	try {
		begin();
		getSession().save(user);
		commit();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	}

	public void update(User user) {
		begin();
		getSession().update(user);
		commit();
	}

	public void delete(User user) {
		begin();
		getSession().delete(user);
		commit();
	}

	public User get(int userid) {
		User user = (User) getSession().get(User.class, userid);
		return user;
	}

	public User verifyuser(String username, String password) {
		System.out.println("FROM User where username = '"+username +"' and password= '"+password+"'");
		Query query = getSession().createQuery("FROM User where username = '"+username +"' and password= '"+password+"'");
		query.setMaxResults(1);
		User user = (User) query.uniqueResult();
		return user; 
	}
	public boolean getemail(String username)
	{
		boolean status = false;
		Query query = getSession().createQuery("FROM User where username = '"+username+"'");
		query.setMaxResults(1);
		User user = (User) query.uniqueResult();
		if(user !=null)
		{
		if(user.getUserName().compareToIgnoreCase(username)==0)
		{
			status = true;
			return status;
		}
		}
		return status;
	}
}
