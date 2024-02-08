package com.mrj.learningportal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mrj.learningportal.dto.UserResponseDto;
import com.mrj.learningportal.entity.UserEntity;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserResponseDto userEntityToDto(UserEntity userEntity);
}
