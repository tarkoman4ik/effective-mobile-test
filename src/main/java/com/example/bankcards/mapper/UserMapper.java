package com.example.bankcards.mapper;

import com.example.bankcards.dto.request.CreateUserRequest;
import com.example.bankcards.dto.response.UserResponse;
import com.example.bankcards.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "username",source = "username")
    @Mapping(target = "password",ignore = true)
    @Mapping(target = "enabled",constant = "true")
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    User toEntity(CreateUserRequest request);

    UserResponse toResponse(User user);
}
