package com.leopbarao.userapi.security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.leopbarao.userapi.controller.exception.UserAPIStandardError;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private ObjectMapper objectMapper = new ObjectMapper();
	
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        
		res.setStatus(HttpStatus.FORBIDDEN.value());
        res.setContentType("application/json;charset=UTF-8"); 			
		
		UserAPIStandardError errorResponse = new UserAPIStandardError(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(),
				"Forbidden", authException.getMessage(), req.getRequestURI());
		
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);			

        res.getOutputStream().println(objectMapper.writeValueAsString(errorResponse));        
    }
}