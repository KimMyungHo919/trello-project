package com.project.trello.domain.member.controller;

import com.project.trello.domain.member.dto.MemberRequestDto;
import com.project.trello.domain.member.dto.MemberResponseDto;
import com.project.trello.domain.member.service.MemberService;
import com.project.trello.domain.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 멤버생성
    @PostMapping
    public ResponseEntity<MemberResponseDto> addMember(
            HttpServletRequest request,
            @RequestBody MemberRequestDto dto
    ) {
        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("user");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.add(loginUser, dto)
                );
    }

}
