package com.leopbarao.userapi.controller.exception;

import java.io.Serializable;

public class UserAPIFieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String field;
	private String message;
	
	public UserAPIFieldMessage(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
