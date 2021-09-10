package com.leopbarao.userapi.services.exception;

public class UserAPIDataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAPIDataIntegrityException(String msg) {
		super(msg);
	}
}
