package com.project.trello.domain.user.controller;

import com.project.trello.domain.user.dto.UserRequestDto;
import com.project.trello.domain.user.dto.UserResponseDto;
import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserResponseDto> join(@RequestBody @Valid UserRequestDto dto) {
        User user = userService.join(dto);
        UserResponseDto userResult = new UserResponseDto(
                user.getUserId(),
                user.getEmail(),
                user.getRole().getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(userResult);
    }
}
