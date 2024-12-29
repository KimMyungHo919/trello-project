package com.project.trello.domain.workspace.controller;

import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.workspace.dto.WorkspaceRequestDto;
import com.project.trello.domain.workspace.dto.WorkspaceResponseDto;
import com.project.trello.domain.workspace.service.WorkspaceService;
import com.project.trello.global.customException.CustomException;
import com.project.trello.global.customException.ExceptionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


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

        WorkspaceResponseDto workspace = workspaceService.create(loginUser.getId(), dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(workspace);
    }

    // 워크스페이스 단건 조회
    @GetMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceResponseDto> findWorkspace(
            @PathVariable Long workspaceId
    ) {
        WorkspaceResponseDto workspace = workspaceService.findWorkspace(workspaceId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(workspace);
    }

    // 워크스페이스 수정
    @PatchMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceResponseDto> updateWorkspace(
            @PathVariable Long workspaceId,
            @RequestBody WorkspaceRequestDto dto
    ) {
        WorkspaceResponseDto workspaceResponseDto = workspaceService.update(workspaceId, dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(workspaceResponseDto);
    }

    // 워크스페이스 삭제
    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<String> deleteWorkspace(
            HttpServletRequest request,
            @PathVariable Long workspaceId
    ) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (!Objects.equals(user.getId(), workspaceId)) {
            throw new CustomException(ExceptionType.WORKSPACE_NOT_FOUND);
        }

        workspaceService.deleteWorkspace(workspaceId);

        return ResponseEntity.status(HttpStatus.OK).body("삭제완료");
    }
}
