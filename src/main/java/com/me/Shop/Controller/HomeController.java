package com.me.Shop.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.me.Shop.Dao.UserDao;
import com.me.Shop.pojo.User;
import com.me.Shop.validator.UserValidator;


@Controller
public class HomeController {


private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
@Autowired
UserValidator uservalidator;
@Autowired
User user;
@Autowired
UserDao userdao;
@GetMapping("/") //Using Simple Form Controller
public String home(User user) {
	return "signup";
}

@PostMapping("/")
public String signup(Model model,User user,SessionStatus sessionStatus, BindingResult result) {
	uservalidator.validate(user, result);
	if(result.hasErrors())
	{
		return "signup";
	}
	else
	{
	model.addAttribute("user", user);
	String temp = user.getUserName();
	boolean exists = userdao.getemail(temp);
	if(exists ==false) {
	user.setRole("customer");
//	UserDao userdao = new UserDao();
	userdao.save(user);
    sessionStatus.setComplete();
	model.addAttribute("createduser",user.getFirstName());
	
	return "useradded";
	}
	else
	{
		model.addAttribute("message","User Name exists. Login or sign up with different user");
		return "signup";
	}
	}
}

@GetMapping("/signin.htm")
public String signin(HttpSession session)
{   
	User user = (User) session.getAttribute("user");
	if( user ==null)
	{
		return "signin";
	}
	
	else if(user.getRole().compareToIgnoreCase("admin")==0)
	{
		return "mainadmin";
	}
	else
	{
		return "user-viewproducts";
	}
}

@RequestMapping(value = "/signin.htm", method = RequestMethod.POST)
public String signinverify(Model model,User user,HttpSession httpsession)
{	
	User userval = userdao.verifyuser(user.getUserName(),user.getPassword());
	if(userval ==null)
	{	
		model.addAttribute("message","Please enter valid credentials");
		return "signin";
	}
	else if(userval.getRole().equalsIgnoreCase("admin"))
	{
		httpsession.setAttribute("user",userval);
		return "mainadmin";
    }
	else if(userval.getRole().equalsIgnoreCase("customer"))
	{
		httpsession.setAttribute("user",userval);
		return "redirect:userview.htm/1";
    }
	return "signin";
}
@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
public String logout(HttpSession httpsession,HttpServletRequest request)
{ 
	HttpSession session1 = request.getSession(true);
	session1.invalidate();
	return "redirect:/signin.htm";
}

}