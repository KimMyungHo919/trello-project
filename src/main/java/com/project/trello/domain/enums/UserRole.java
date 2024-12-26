package com.project.trello.domain.enums;

import com.project.trello.global.CustomException;
import com.project.trello.global.ExceptionType;
import lombok.Getter;

@Getter
public enum UserRole {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public static UserRole of(String roleName) {
        for (UserRole role : values()) {
            if (role.getName().equals(roleName)) {
                return role;
            }
        }

        throw new CustomException(ExceptionType.EXIST_USER_ROLE);
    }
}
