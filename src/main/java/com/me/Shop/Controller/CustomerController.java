package com.me.Shop.Controller;
import com.itextpdf.html2pdf.HtmlConverter;
import com.me.Shop.cartBuilder.CartBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.me.Shop.Dao.AddressDao;
import com.me.Shop.Dao.OrderDao;
import com.me.Shop.Dao.ProductDao;
import com.me.Shop.Dao.UserDao;
import com.me.Shop.pojo.Cart;
import com.me.Shop.pojo.CustomerAddress;
import com.me.Shop.pojo.Order;
import com.me.Shop.pojo.OrderLine;
import com.me.Shop.pojo.ProductInfo;
import com.me.Shop.pojo.User;
import com.me.Shop.validator.UserValidator;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
@Controller
public class CustomerController {
	@Autowired
	UserValidator uservalidator;
	@Autowired
	User user;
	@Autowired
	ProductDao productdao;
	@Autowired
	OrderDao orderdao;
	@Autowired
	CustomerAddress address;
	@Autowired
	AddressDao addressdao;
	@Autowired
	ProductInfo product;
	@Autowired
	Cart cart;
	@Autowired
	CartBuilder cartbuilder;
	@Autowired
	Order order;
	@Autowired
	OrderLine orderline;
	
	
	@GetMapping("/userview.htm/{pageid}") //Using Simple Form Controller
	public String home(HttpServletRequest request,HttpSession session,@PathVariable int pageid) {
		long prodCount = productdao.countQuery();
		System.out.println(prodCount);
		long pagesNumber = (prodCount/2);
		pageid = pageid-1;
		int pagend = pageid+3;
		System.out.println(pageid);
		System.out.println(pagend);
		System.out.println("!@################");
		List<ProductInfo> products = productdao.ProductList(pageid,pagend);
		System.out.println("!@################end");
		session.setAttribute("pagecount",pagesNumber);
		session.setAttribute("productlist",products);
		return "user-viewproducts";
	}
	
	@PostMapping("/addcart.htm")
	public String addtocart(@RequestParam("quantity") String[] quantity,@RequestParam("id") String[] id,@RequestParam("productName") String[] productName,@RequestParam("productDesc") String[] productDesc,@RequestParam("price") String[] price,@RequestParam("imgname") String[] imgname,Order order,HttpSession session,User user,OrderDao orderdao,Model model)
	{	boolean flag = false;
		for(int i=0;i<quantity.length;i++)
	{
		if(quantity[i].isEmpty())
		{
			
		}
		else
		{	
			flag = true;
		}
	}
		
		user = (User)session.getAttribute("user");
		if(user== null)
		{
			return "unauthorized";
		}
		else if(flag == false)
		{  model.addAttribute("message","Add  Items to view cart");
			return "user-viewproducts";
		}
		else
		{
		CartBuilder cart = new CartBuilder();
	    ArrayList<Cart> cartrt = cart.addtocart(id, productName, productDesc, price, quantity,imgname,session);
		System.out.println(id);
		System.out.println(quantity);
		user = (User)session.getAttribute("user");
		order.setUser(user);
		Date createdDate = new Date();
		Date date = new Date();
	
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SS");
			String val = formatter.format(date);
			try {
				java.util.Date utilTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(val);
				System.out.println(utilTimestamp);
				order.setCreatedDate(date.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Cart> cartt =  (ArrayList<Cart>) session.getAttribute("cart");
		return "redirect:/userview.htm/1";
	
		}
		}
	@GetMapping("/addaddress.htm")
	public String addaddresspage()
	{
		
		return "addaddress";
	}
	@PostMapping("/addaddress.htm")
	public String postaddress(@ModelAttribute("address")CustomerAddress address,HttpSession session)
	{	
		System.out.println(product);
		User user = (User) session.getAttribute("user");
		address.setUser(user);
		addressdao.save(address);
		return "redirect:/addaddress.htm";
	}
	@GetMapping("/viewcart.htm")
	public String getcart(HttpSession session,User user,Model model)
	{	if((User) session.getAttribute("user") ==null)
	{
		return "unauthorized";
	}
	else
	{
		ArrayList<Cart> cartt =  (ArrayList<Cart>) session.getAttribute("cart");
		float totalPrice = cartbuilder.totalPrice(cartt);
		if(totalPrice==0)
		{   model.addAttribute("message","Please add products to cart to view the cart");
			return "redirect:userview.htm/1";
		}
		else
		{
		session.setAttribute("total", totalPrice);
		user = (User)session.getAttribute("user");
		List<CustomerAddress> AddressList = addressdao.AddressList(user.getId());
		session.setAttribute("addresslist",AddressList);
		return "viewcart";
		}
	}
	}
	
	@PostMapping("/placeorder.htm") 
	String placeorder(HttpSession session,@RequestParam("addressid") String[] id,Model model,@RequestParam("address") String[] val)
	{	
		 Date date = new Date();
	     order.setCreatedDate(date.toString());
		 order.setUser((User)session.getAttribute("user"));
		 
//		 address = addressdao.getaddress(Integer.parseInt(id[0]));
		 address = addressdao.getaddress(Integer.parseInt(val[0]));
		 order.setAddress(address);
		 order.setPrice((float) session.getAttribute("total"));
		 ArrayList<Cart> cartt =  (ArrayList<Cart>) session.getAttribute("cart");
	     orderdao.save(order,cartt);
	     model.addAttribute("message","Your order has been successfully placed");
	     session.removeAttribute("cart");
		 return "user-viewproducts";
	}
	
	@GetMapping("/orderhistory.htm")
	String loadorderhistory(HttpSession session,ModelMap model)
	{  
	User user = (User)session.getAttribute("user");
	List<Order> orderslist = orderdao.OrderList(user.getId());
	if(orderslist.isEmpty())
	{
		return "hello";
	}
	else
		{
		model.addAttribute("orderhistory",orderslist);
		
		return "orderhistory";
		}
	}
	@GetMapping("/orderdetails.htm/{orderid}")
	String orderdetails(HttpSession session,ModelMap model,@PathVariable int orderid)
	{ 
		List<OrderLine> orderslist = orderdao.Orderlineitems(orderid);
		for(OrderLine c:orderslist)
		{
			System.out.println(c.getProductname());
		}
		float total = orderdao.getTotalPrice(orderid);
		model.addAttribute("orderdetailist",orderslist);
		model.addAttribute("totalprice", total);
		return "order-details";
	}
	
	@GetMapping("/ordertable.htm/{orderid}")
	String ordertable(HttpSession session,ModelMap model,@PathVariable int orderid)
	{ 
		List<OrderLine> orderslist = orderdao.Orderlineitems(orderid);
		String personName= null;
		String address1=null;
		String address2=null;
		String OrderId=null;
		for(OrderLine c:orderslist)
		{
			OrderId = String.valueOf(c.getOrder().getOrderID());
			address1 = c.getOrder().getAddress().getApartmentNumber()+", "+c.getOrder().getAddress().getStreetName();
			address2 = c.getOrder().getAddress().getCity()+", "+c.getOrder().getAddress().getZipCode();
			personName = c.getOrder().getUser().getFirstName()+"  "+ c.getOrder().getUser().getLastName();
			break;
		}
		float total = orderdao.getTotalPrice(orderid);
		model.addAttribute("OrderId",OrderId);
		model.addAttribute("adddress1",address1);
		model.addAttribute("address2",address2);
		model.addAttribute("personname", personName);
		model.addAttribute("orderdetailist",orderslist);
		model.addAttribute("totalprice", total);
		return "ordertable";
	}
	
	@GetMapping("/generatepdf.htm/{orderid}")
	public ResponseEntity<?> loadpdf(@PathVariable int orderid,Model model) throws IOException
	{   URL u;
	 String vari = null;
	ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
	try {
		u = new URL("http://localhost:8080/shop/ordertable.htm/"+orderid);
		Scanner scanner = new Scanner(u.openStream());
	      StringBuffer stringbuffer = new StringBuffer();
	      while(scanner.hasNext()) {
	    	  stringbuffer.append(scanner.next());
	         
	      }
	      String result = stringbuffer.toString();
		InputStream inputstream = null;
		inputstream = u.openStream ();
		  byte[] bytes = new byte[100000000]; 
		  int n;
		  while ( (n = inputstream.read(bytes)) > 0 ) {
			  outputstream.write(bytes, 0, n);
			  }
		
		   ConverterProperties converterProperties = new ConverterProperties();
		    converterProperties.setBaseUri("http://localhost:8080/shop/file.pdf");
		  HtmlConverter.convertToPdf(result,outputstream,converterProperties);
		  
	} 
	catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	byte[] val = outputstream.toByteArray();
	return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .body(val);
	}

}
