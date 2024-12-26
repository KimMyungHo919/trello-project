package com.project.trello.domain.user.controller;

import com.project.trello.domain.user.dto.UserLoginRequestDto;
import com.project.trello.domain.user.dto.UserRequestDto;
import com.project.trello.domain.user.dto.UserResponseDto;
import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.user.service.UserService;
import com.project.trello.global.customException.CustomException;
import com.project.trello.global.customException.ExceptionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<UserResponseDto> join(@RequestBody @Valid UserRequestDto dto) {
        User user = userService.join(dto);
        UserResponseDto userResult = new UserResponseDto(
                user.getUserId(),
                user.getEmail(),
                user.getRole().getName()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResult);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            HttpServletRequest request,
            @RequestBody UserLoginRequestDto dto
    ) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            throw new CustomException(ExceptionType.ALREADY_LOGIN);
        }

        User user = userService.login(dto);

        session = request.getSession(true);
        session.setAttribute("user", user);

        UserResponseDto resultUser = new UserResponseDto(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultUser);
    }

    // 탈퇴
}
