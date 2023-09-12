package com.me.Shop.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.me.Shop.Dao.ProductDao;
import com.me.Shop.pojo.ProductInfo;
import com.me.Shop.pojo.User;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	User user;
	@Autowired
	ProductInfo product;
	@Autowired
	ProductDao productdao;
	private static final String IMAGE_DIRECTORY ="/images";  
	@RequestMapping(value = "/upload.htm", method = RequestMethod.POST)
	public String upload(@ModelAttribute("product")ProductInfo product,HttpSession session,User user,HttpServletRequest request) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date date = new Date();

		try{  
			System.out.println(product.getImgfile().getOriginalFilename());
			 System.out.println(System.getProperty("user.dir"));
	        String newfilename = formatter.format(date)+'.'+FilenameUtils.getExtension(product.getImgfile().getOriginalFilename());
	        System.out.println(newfilename);
	        System.out.println("!!!!!!############");
	        product.getImgfile().transferTo(new File(System.getProperty("user.dir")+"\\src\\main\\webapp\\images\\"+newfilename));
	        product.setImgname(newfilename);
	        }
		catch(Exception e)
		{
			System.out.println(e);
	    } 
		
		User user1 = (User) session.getAttribute("user");
		
		product.setUser(user1);
//		ProductDao productdao = new ProductDao();
		productdao.save(product);
		return "mainadmin";
		}


	@RequestMapping(value = "/viewproducts.htm/{pageid}", method = RequestMethod.GET)
	public String viewproducts(HttpServletRequest request,HttpSession session,@PathVariable int pageid,User user)
	{ 	user = (User) session.getAttribute("user");
		if(user==null || user.getRole().compareToIgnoreCase("customer")==0)
		{
			
			return "unauthorized";
		}
		else
		{
		System.out.println(session.getServletContext().getRealPath(IMAGE_DIRECTORY)); 
		long prodCount = productdao.countQuery();
		System.out.println(prodCount);
		long pagesNumber = (prodCount/2);
		pageid = pageid-1;
		int pagend = pageid+3;
		System.out.println(pageid);
		System.out.println(pagend);
		List<ProductInfo> products = productdao.ProductList(pageid,pagend);
		System.out.println("!@################end");
		session.setAttribute("pagecount",pagesNumber);
		session.setAttribute("productlist",products);
		return "viewproducts";
		}
	}
	
	@RequestMapping(value = "/updateproduct.htm/{id}", method = RequestMethod.GET)
	public String updateproduct(HttpServletRequest request,HttpSession session,@PathVariable String id,ModelMap model)
	{ 	
		ProductInfo info = productdao.getproductinfo(id);
		request.setAttribute("prod",info);
		model.addAttribute("prod",info);
		return "editproduct";
	}
	@RequestMapping(value = "/updateproduct.htm/{id}", method = RequestMethod.POST)
	public String updateproductinfo(Model model,User user,ProductInfo product,SessionStatus sessionStatus, BindingResult result)
	{
		
		return "viewproducts";
	}
	@RequestMapping(value = "/deleteproduct.htm/{id}", method = RequestMethod.GET)
	public String deleteproduct(@PathVariable String id,HttpSession session)
	{	user = (User) session.getAttribute("user");
	if(user==null || user.getRole().compareToIgnoreCase("customer")==0)
	{
		
		return "unauthorized";
	}
	else
	{
		boolean val = productdao.deleteproductinfo(id);
		if(val==true)
		{
		return "redirect:/viewproducts.htm/1";
		}
		else
		{
			return "viewproducts";
		}
	}
	}
	@RequestMapping(value = "/updateproduct.htm", method = RequestMethod.POST)
	public String delete(@ModelAttribute("prod")ProductInfo product,User user,HttpSession session)
	{	user = (User) session.getAttribute("user");
		product.setUser(user);
		
		System.out.println(product.getImgname());
	   System.out.println(user.getFirstName());
		System.out.println(product.getPrice());
		if(product.getImgfile().getSize()>0)
		{
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date date = new Date();
		try{  

	        String newfilename = formatter.format(date)+'.'+FilenameUtils.getExtension(product.getImgfile().getOriginalFilename());
	        product.getImgfile().transferTo(new File(System.getProperty("user.dir")+"\\src\\main\\webapp\\images\\"+newfilename));
	        product.setImgname(newfilename);
	        }
		catch(Exception e)
		{
			System.out.println(e);
	    } 
		}
		productdao.updateproduct(product);
		return "redirect:viewproducts.htm/1";
	}
	
	
	
}
