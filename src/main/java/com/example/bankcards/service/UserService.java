package com.example.bankcards.service;

import com.example.bankcards.dto.request.CreateUserRequest;
import com.example.bankcards.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
}
