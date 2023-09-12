package com.me.Shop.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.Shop.pojo.CustomerAddress;
import com.me.Shop.pojo.User;

@Component
public class AddressValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return CustomerAddress.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors)
	{
		ValidationUtils.rejectIfEmpty(errors, "StreetName","empty street" ,"First name cannot be blank empty");
//		ValidationUtils.rejectIfEmpty(errors, "lastName","empty last" ,"Last name cannot be empty");
//		ValidationUtils.rejectIfEmpty(errors, "userName","empty user" ,"UserName is cannot be empty");
//		ValidationUtils.rejectIfEmpty(errors,"password", "empty password","Password cannot be Empty");

	}
}
