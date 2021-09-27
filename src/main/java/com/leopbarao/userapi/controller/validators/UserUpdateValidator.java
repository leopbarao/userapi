package com.leopbarao.userapi.controller.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.leopbarao.userapi.controller.exception.UserAPIFieldMessage;
import com.leopbarao.userapi.dto.UserDTO;
import com.leopbarao.userapi.services.UserService;
import com.leopbarao.userapi.utils.StringUtil;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateConstraint, UserDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(UserDTO user, ConstraintValidatorContext context) {

		List<UserAPIFieldMessage> listErrors = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		if (StringUtil.isNumeric(map.get("id"))) {
			Long uriId = Long.parseLong(map.get("id"));

			UserDTO userDto = userService.findByEmail(user.getEmail());

			if (userDto != null && !userDto.getId().equals(uriId)) {
				listErrors.add(new UserAPIFieldMessage("email", "Email is already in use"));
			}			
		} else {
			listErrors.add(new UserAPIFieldMessage("id", "ID must be numeric"));
		}
		
		for (UserAPIFieldMessage e : listErrors) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}
		
		return listErrors.isEmpty();
	}
}
