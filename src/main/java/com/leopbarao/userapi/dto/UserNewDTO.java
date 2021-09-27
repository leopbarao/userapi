package com.leopbarao.userapi.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leopbarao.userapi.controller.validators.UserInsertConstraint;
import com.leopbarao.userapi.model.UserModel;
import com.leopbarao.userapi.model.enums.ProfileEnum;

@UserInsertConstraint
public class UserNewDTO  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Required field")
	@Length(min = 5, max = 140, message = "Size must be between 5 and 120 characters")
	private String name;

	@NotBlank(message = "Required field")
	@Email(message = "Invalid email")
	private String email;

	@NotNull(message = "Required field")
	private LocalDate birthDate;

	private Set<ProfileEnum> profiles = new HashSet<>();

	@NotBlank(message = "Required field")
	private String password;

	private AddressDTO address;

	public UserNewDTO() {
	}

	public UserNewDTO(UserModel userModel) {
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
