package com.leopbarao.userapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leopbarao.userapi.model.UserModel;
import com.leopbarao.userapi.repositories.UserRepository;
import com.leopbarao.userapi.security.UserSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		UserModel user = userRepo.findByEmail(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new UserSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getProfiles());
	}

}
