package com.project.trello.domain.workspace.controller;

import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.workspace.dto.WorkspaceRequestDto;
import com.project.trello.domain.workspace.dto.WorkspaceResponseDto;
import com.project.trello.domain.workspace.entity.Workspace;
import com.project.trello.domain.workspace.service.WorkspaceService;
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
@RequestMapping("/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    // 워크스페이스 생성. ADMIN 권한을 가진 유저만 생성가능.
    @PostMapping
    public ResponseEntity<WorkspaceResponseDto> createWorkspace(
            HttpServletRequest request,
            @RequestBody @Valid WorkspaceRequestDto dto
    ) {
        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("user");

        WorkspaceResponseDto workspace = workspaceService.create(loginUser, dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(workspace);
    }
}
