package com.leopbarao.userapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leopbarao.userapi.dto.UserDTO;
import com.leopbarao.userapi.services.UserService;

@RestController
@RequestMapping(value = "/userapi")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> lstUser = userService.findAll();
		return ResponseEntity.ok(lstUser);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO userDto) {
		UserDTO user = userService.insert(userDto);
		return ResponseEntity.created(URI.create("/userapi/" + user.getId())).body(user);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findOne(@Valid @PathVariable Long id) {
		UserDTO user = userService.findById(id);
		return ResponseEntity.ok(user);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDto, @Valid @PathVariable Long id) {
		userDto.setId(id);
		userService.update(userDto);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/usersPerPage", method = RequestMethod.GET)
	public ResponseEntity<Page<UserDTO>> pagedQuery(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "30") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<UserDTO> pagedUsers = userService.findAll(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(pagedUsers);
	}
}
