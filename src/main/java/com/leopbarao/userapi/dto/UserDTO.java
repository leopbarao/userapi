package com.leopbarao.userapi.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leopbarao.userapi.controller.validators.UserUpdateConstraint;
import com.leopbarao.userapi.model.UserModel;
import com.leopbarao.userapi.model.enums.ProfileEnum;

@UserUpdateConstraint
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	private LocalDate birthDate;
	private String password;
	private AddressDTO address;

	private Set<ProfileEnum> profiles;
	
	public UserDTO() {
	}

	public UserDTO(UserModel userModel) {
		super();
		id = userModel.getId();
		name = userModel.getName();
		email = userModel.getEmail();
		birthDate = userModel.getBirthDate();
		profiles = userModel.getProfiles();
		
		if (userModel.getAddress() != null) {
			AddressDTO addressDTO = new AddressDTO(userModel.getAddress());
			address = addressDTO;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Set<ProfileEnum> getProfiles() {
		return profiles;
	}

	public void addProfile(ProfileEnum profile) {
		profiles.add(profile);
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}
}
