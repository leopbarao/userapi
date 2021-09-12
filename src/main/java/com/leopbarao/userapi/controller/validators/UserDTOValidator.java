package com.leopbarao.userapi.controller.validators;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.leopbarao.userapi.dto.UserDTO;
import com.leopbarao.userapi.services.UserService;

public class UserDTOValidator implements ConstraintValidator<UserDTOConstraint, UserDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
		
		return true;
	}
}
