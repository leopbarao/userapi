package com.leopbarao.userapi.controller.exception;

public class UserAPIAuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserAPIAuthorizationException(String msg) {
		super(msg);
	}	

}
