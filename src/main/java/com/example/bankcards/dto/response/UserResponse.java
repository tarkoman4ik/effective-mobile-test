package com.example.bankcards.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String role;
    private boolean enabled;
    private LocalDateTime createdAt;
}
