package com.me.Shop.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.me.Shop.pojo.User;
import com.me.Shop.pojo.ProductInfo;

@Component
public class ProductValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ProductInfo.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "productName","empty productName" ,"First name cannot be blank empty");
//		ValidationUtils.rejectIfEmpty(errors, "lastName","empty last" ,"Last name is empty");
	}

}
