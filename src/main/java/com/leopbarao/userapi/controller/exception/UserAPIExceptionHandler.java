package com.leopbarao.userapi.controller.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.leopbarao.userapi.services.exception.UserAPIDataIntegrityException;
import com.leopbarao.userapi.services.exception.UserAPIObjectNotFoundException;

@ControllerAdvice
public class UserAPIExceptionHandler {

	@ExceptionHandler(UserAPIObjectNotFoundException.class)
	public ResponseEntity<UserAPIStandardError> objectNotFound(UserAPIObjectNotFoundException e, HttpServletRequest request) {

		UserAPIStandardError error = new UserAPIStandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Object not found", e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(UserAPIDataIntegrityException.class)
	public ResponseEntity<UserAPIStandardError> dataIntegrity(UserAPIDataIntegrityException e, HttpServletRequest request) {
		
		UserAPIStandardError error = new UserAPIStandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"Data integrity", e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<UserAPIValidationError> dataValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		UserAPIValidationError error = new UserAPIValidationError(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Data validation", e.getMessage(), request.getRequestURI());
		
		for (FieldError f : e.getFieldErrors()) {
			UserAPIFieldMessage field = new UserAPIFieldMessage(f.getField(), f.getDefaultMessage());
			error.addError(field);
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<UserAPIStandardError> notFound(NoHandlerFoundException e, HttpServletRequest request) {
		UserAPIStandardError error = new UserAPIStandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Resource not found", e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);		
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<UserAPIStandardError> invalidArgument(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
		UserAPIStandardError error = new UserAPIStandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"Invalid argument", e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);		
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<UserAPIStandardError> invalidMessage(HttpMessageNotReadableException e, HttpServletRequest request) {
		UserAPIStandardError error = new UserAPIStandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"Invalid message", e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);		
	}
}
