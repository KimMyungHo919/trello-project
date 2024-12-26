package com.project.trello.domain.user.dto;

import com.project.trello.domain.user.entity.User;
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

    public UserResponseDto(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole().getName();
    }
}
