package com.leopbarao.userapi.model.enums;

import java.util.Arrays;

public enum ProfileEnum {

	USER(1, "ROLE_USER"),
	MANAGER(2, "ROLE_MANAGER"),
	HR(3, "ROLE_HR"),
	ADMIN(4, "ROLE_ADMIN");

	private Integer code;
	private String description;

	private ProfileEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static ProfileEnum convertToEnum(Integer code) {

		if (code == null) {
			return null;
		}

		return Arrays.asList(ProfileEnum.values()).stream()
				.filter(p -> p.getCode().equals(code))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid code: " + code));
	}
}
