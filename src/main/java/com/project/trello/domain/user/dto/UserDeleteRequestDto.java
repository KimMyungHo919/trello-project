package com.project.trello.domain.user.dto;

import lombok.Getter;

@Getter
public class UserDeleteRequestDto {

    private String email;
    private String password;
}
