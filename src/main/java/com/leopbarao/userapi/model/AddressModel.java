package com.leopbarao.userapi.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_address")
public class AddressModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String publicArea;
	private String number;
	private String additionalNumber;
	private String district;
	private String zipCode;
	private String city;
	private String state;

	@JsonIgnore
	@OneToOne(mappedBy = "address")
	private UserModel user;

	public AddressModel() {
	}

	public AddressModel(Long id, String publicArea, String number, String additionalNumber, String district,
			String zipCode, String city, String state) {
		super();
		this.id = id;
		this.publicArea = publicArea;
		this.number = number;
		this.additionalNumber = additionalNumber;
		this.district = district;
		this.zipCode = zipCode;
		this.city = city;
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		AddressModel other = (AddressModel) obj;
		return Objects.equals(id, other.id);
	}
}
