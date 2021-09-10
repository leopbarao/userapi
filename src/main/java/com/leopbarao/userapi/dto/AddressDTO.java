package com.leopbarao.userapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leopbarao.userapi.model.AddressModel;

public class AddressDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Long id;

	private String publicArea;

	@NotEmpty(message = "Required field")
	private String number;

	private String additionalNumber;
	private String district;

	@NotEmpty(message = "Required field")
	private String zipCode;
	private String city;
	private String state;

	public AddressDTO() {
	}

	public AddressDTO(AddressModel addressModel) {
		id = addressModel.getId();
		publicArea = addressModel.getPublicArea();
		number = addressModel.getNumber();
		additionalNumber = addressModel.getAdditionalNumber();
		district = addressModel.getDistrict();
		zipCode = addressModel.getZipCode();
		city = addressModel.getCity();
		state = addressModel.getState();
	}

	public String getPublicArea() {
		return publicArea;
	}

	public void setPublicArea(String publicArea) {
		this.publicArea = publicArea;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAdditionalNumber() {
		return additionalNumber;
	}

	public void setAdditionalNumber(String additionalNumber) {
		this.additionalNumber = additionalNumber;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
