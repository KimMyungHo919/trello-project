package com.project.trello.domain.user.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long userId;
    private final String email;
    private final String role;

    public UserResponseDto(Long userId, String email, String role) {
        this.userId = userId;
        this.email = email;
        this.role = role;
    }
}
