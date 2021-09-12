package com.leopbarao.userapi.controller.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserAPIStandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDateTime dateTime;
	private Integer httpCode;
	private String error;
	private String message;
	private String path;
	
	public UserAPIStandardError(LocalDateTime dateTime, Integer httpCode, String error, String message, String path) {
		super();
		this.dateTime = dateTime;
		this.httpCode = httpCode;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}	
}
