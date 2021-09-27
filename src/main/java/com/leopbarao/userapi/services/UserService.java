package com.leopbarao.userapi.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.leopbarao.userapi.dto.UserDTO;
import com.leopbarao.userapi.dto.UserNewDTO;

public interface UserService {

	List<UserDTO> findAll();

	Page<UserDTO> findAll(Integer page, Integer linesPerPage, String orderBy, String direction);

	UserDTO findById(Long id);
	
	UserDTO findByEmail(String email);

	UserNewDTO insert(UserNewDTO user);

	UserDTO update(UserDTO user);

	void delete(Long id);
}
