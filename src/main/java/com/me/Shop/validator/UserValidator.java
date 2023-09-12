package com.me.Shop.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.Shop.pojo.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "firstName","empty first" ,"First name cannot be blank empty");
		ValidationUtils.rejectIfEmpty(errors, "lastName","empty last" ,"Last name cannot be empty");
		ValidationUtils.rejectIfEmpty(errors, "userName","empty user" ,"UserName is cannot be empty");
		ValidationUtils.rejectIfEmpty(errors,"password", "empty password","Password cannot be Empty");
//		ValidationUtils.rejectIfEmpty(errors,"")
		
		
	}

}
