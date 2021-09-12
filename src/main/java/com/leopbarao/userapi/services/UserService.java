package com.leopbarao.userapi.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.leopbarao.userapi.dto.UserDTO;

public interface UserService {

	List<UserDTO> findAll();

	Page<UserDTO> findAll(Integer page, Integer linesPerPage, String orderBy, String direction);

	UserDTO findById(Long id);
	
	UserDTO findByEmail(String email);

	UserDTO insert(UserDTO user);

	void update(UserDTO user);

	void delete(Long id);
}
