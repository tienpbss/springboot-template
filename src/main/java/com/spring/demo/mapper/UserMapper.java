package com.spring.demo.mapper;

import com.spring.demo.dto.UserCreateDto;
import com.spring.demo.dto.UserResponseDto;
import com.spring.demo.dto.UserUpdateDto;
import com.spring.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);
    User toEntity(UserCreateDto user);
    List<UserResponseDto> toDtos(List<User> users);
    void updateEntityFromDto(UserUpdateDto userUpdateDto, @MappingTarget User user);
}