package com.leopbarao.userapi.controller.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserAPIValidationError extends UserAPIStandardError {

	private static final long serialVersionUID = 1L;

	private List<UserAPIFieldMessage> listMessage = new ArrayList<>();
	
	public UserAPIValidationError(LocalDateTime dateTime, Integer httpCode, String error, String message, String path) {
		super(dateTime, httpCode, error, message, path);
	}
	
	public List<UserAPIFieldMessage> getErrors() {
		return listMessage;
	}
	
	public void addError(UserAPIFieldMessage field) {
		listMessage.add(field);
	}
	
}
