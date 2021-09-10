package com.leopbarao.userapi.services.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leopbarao.userapi.dto.AddressDTO;
import com.leopbarao.userapi.dto.UserDTO;
import com.leopbarao.userapi.model.AddressModel;
import com.leopbarao.userapi.model.UserModel;
import com.leopbarao.userapi.model.enums.ProfileEnum;
import com.leopbarao.userapi.repositories.UserRepository;
import com.leopbarao.userapi.services.UserService;
import com.leopbarao.userapi.services.exception.UserAPIDataIntegrityException;
import com.leopbarao.userapi.services.exception.UserAPIObjectNotFoundException;
import com.leopbarao.userapi.utils.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserDTO> findAll() {
		return userRepo.findAll().stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
	}

	@Override
	public Page<UserDTO> findAll(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<UserModel> pageUser = userRepo.findAll(pageRequest);
		Page<UserDTO> pageUserDto = pageUser.map(new Function<UserModel, UserDTO>() {
			@Override
			public UserDTO apply(UserModel t) {
				return new UserDTO(t);
			}
		});
		return pageUserDto;
	}

	@Override
	public UserDTO findById(Long id) {
		return userRepo.findById(id).map(u -> new UserDTO(u))
				.orElseThrow(() -> new UserAPIObjectNotFoundException("User not found: " + id));
	}

	@Transactional
	@Override
	public UserDTO insert(UserDTO user) {
		UserModel model = userRepo.save(userFromDTO(user));
		return new UserDTO(model);
	}

	@Transactional
	@Override
	public void update(UserDTO user) {
		if (user.getId() == null) {
			throw new UserAPIObjectNotFoundException("User ID not found");
		}

		UserModel model = userRepo.getById(user.getId());
		userMapper.updateUserFromDto(user, model);
		userRepo.save(model);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		if (id == null) {
			throw new UserAPIObjectNotFoundException("User ID not found");
		}

		try {
			userRepo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new UserAPIDataIntegrityException("Delete not possible. User has links.");
		}
	}

	private UserModel userFromDTO(UserDTO newUserDto) {

		if (newUserDto != null) {
			UserModel model = new UserModel();
			model.setId(newUserDto.getId());
			model.setName(newUserDto.getName());
			model.setEmail(newUserDto.getEmail());
			model.setPassword(pwdEncoder.encode(newUserDto.getPassword()));
			model.setBirthDate(newUserDto.getBirthDate());

			if (newUserDto.getAddress() != null) {
				model.setAddress(addressFromDTO(newUserDto.getAddress()));
			}

			for (ProfileEnum profile : newUserDto.getProfiles()) {
				model.addProfile(profile);
			}

			return model;
		}

		return null;
	}

	private AddressModel addressFromDTO(AddressDTO addressDto) {
		if (addressDto != null) {
			AddressModel model = new AddressModel();
			model.setPublicArea(addressDto.getPublicArea());
			model.setNumber(addressDto.getNumber());
			model.setAdditionalNumber(addressDto.getAdditionalNumber());
			model.setDistrict(addressDto.getDistrict());
			model.setZipCode(addressDto.getZipCode());
			model.setCity(addressDto.getCity());
			model.setState(addressDto.getState());

			return model;
		}

		return null;
	}
}
