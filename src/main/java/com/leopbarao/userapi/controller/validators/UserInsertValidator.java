package com.leopbarao.userapi.controller.validators;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.leopbarao.userapi.controller.exception.UserAPIFieldMessage;
import com.leopbarao.userapi.dto.UserDTO;
import com.leopbarao.userapi.dto.UserNewDTO;
import com.leopbarao.userapi.services.UserService;

public class UserInsertValidator implements ConstraintValidator<UserInsertConstraint, UserNewDTO> {

	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(UserNewDTO user, ConstraintValidatorContext context) {

		List<UserAPIFieldMessage> listErrors = new ArrayList<>();

		UserDTO userDto = userService.findByEmail(user.getEmail());

		if (userDto != null) {
			listErrors.add(new UserAPIFieldMessage("email", "Email is already in use"));
		}

		for (UserAPIFieldMessage e : listErrors) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}
		
		return listErrors.isEmpty();
	}
}
