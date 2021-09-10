package com.leopbarao.userapi.utils;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.leopbarao.userapi.dto.UserDTO;
import com.leopbarao.userapi.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateUserFromDto(UserDTO dto, @MappingTarget UserModel model);
}
