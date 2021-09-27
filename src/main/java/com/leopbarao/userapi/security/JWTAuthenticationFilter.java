package com.leopbarao.userapi.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.leopbarao.userapi.controller.exception.UserAPIStandardError;
import com.leopbarao.userapi.dto.LoginDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	private JWTUtil jwtUtil;

	public JWTAuthenticationFilter(AuthenticationManager authManager, JWTUtil jwtUtil) {
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.authenticationManager = authManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			LoginDTO loginDto = new ObjectMapper().readValue(req.getInputStream(), LoginDTO.class);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
					loginDto.getPassword(), new ArrayList<>());

			Authentication auth = authenticationManager.authenticate(authToken);

			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String username = ((UserSecurity) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		res.addHeader("Authorization", "Bearer " + token);
		res.addHeader("access-control-expose-headers", "Authorization");
	}

	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

		private ObjectMapper objectMapper = new ObjectMapper();

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {

			response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8"); 			
			
			UserAPIStandardError errorResponse = new UserAPIStandardError(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),
					"Unauthorized", exception.getMessage(), request.getRequestURI());
			
	        objectMapper.registerModule(new JavaTimeModule());
	        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);			

			response.getOutputStream().println(objectMapper.writeValueAsString(errorResponse));
		}
	}
}
