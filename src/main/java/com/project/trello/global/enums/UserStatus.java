package com.project.trello.global.enums;

import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String name;

    UserStatus(String name) {
        this.name = name;
    }
}
