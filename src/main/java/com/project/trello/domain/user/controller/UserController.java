package com.project.trello.domain.user.controller;

import com.project.trello.domain.user.dto.*;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                user.getId(),
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

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();

        return ResponseEntity.status(HttpStatus.OK).body("로그아웃되었습니다.");
    }


    // 탈퇴
    @PatchMapping("/{id}/inactive")
    public ResponseEntity<String> deleteUser(
            HttpServletRequest request,
            @RequestBody UserDeleteRequestDto dto,
            @PathVariable Long id
    ) {
        userService.deleteUser(dto, id);

        HttpSession session = request.getSession(false);
        session.invalidate();

        return ResponseEntity.status(HttpStatus.OK).body("탈퇴가 완료되었습니다.");
    }

    // 자신이 속해있는 워크스페이스 정보 전체조회
    @GetMapping
    public List<UserWorkspaceResponseDto> findWorkspaceList(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        return userService.findWorkspaceList(user.getId());
    }

}
