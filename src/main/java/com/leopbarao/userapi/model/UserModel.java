package com.leopbarao.userapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.leopbarao.userapi.model.enums.ProfileEnum;

@Entity
@Table(name = "tb_users")
public class UserModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;

	private LocalDate birthDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressModel address;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
			name = "tb_users_profile",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
	)
	@Column(name = "profile_id")
	private Set<Integer> profiles = new HashSet<>();

	public UserModel() {
		addProfile(ProfileEnum.USER);
	}

	public UserModel(Long id, String name, String email, String password, LocalDate birthDate, AddressModel address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.address = address;
		addProfile(ProfileEnum.USER);
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public Set<ProfileEnum> getProfiles() {
		return profiles.stream().map(p -> ProfileEnum.convertToEnum(p)).collect(Collectors.toSet());
	}

	public void addProfile(ProfileEnum profile) {
		profiles.add(profile.getCode());
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
		UserModel other = (UserModel) obj;
		return Objects.equals(id, other.id);
	}
}
