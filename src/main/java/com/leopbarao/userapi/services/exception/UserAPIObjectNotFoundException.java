package com.leopbarao.userapi.services.exception;

public class UserAPIObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAPIObjectNotFoundException(String msg) {
		super(msg);
	}
}
