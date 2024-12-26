package com.project.trello.domain.workspace.service;

import com.project.trello.domain.member.entity.Member;
import com.project.trello.domain.member.repository.MemberRepository;
import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.workspace.dto.WorkspaceRequestDto;
import com.project.trello.domain.workspace.dto.WorkspaceResponseDto;
import com.project.trello.domain.workspace.entity.Workspace;
import com.project.trello.domain.workspace.repository.WorkspaceRepository;
import com.project.trello.global.customException.CustomException;
import com.project.trello.global.customException.ExceptionType;
import com.project.trello.global.enums.MemberRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final MemberRepository memberRepository;

    // 워크스페이스 생성. ADMIN 권한을 가진 유저만 생성가능.
    @Transactional
    public WorkspaceResponseDto create(User loginUser, WorkspaceRequestDto dto) {
        if (!Objects.equals("ROLE_ADMIN", loginUser.getRole().getName())) {
            throw new CustomException(ExceptionType.ROLE_NOT_CORRECT);
        }

        Workspace workspace = new Workspace(dto.getTitle(), dto.getSubTitle());
        workspaceRepository.save(workspace);

        Member member = new Member(loginUser, workspace, MemberRole.ROLE_ADMIN);
        memberRepository.save(member);

        return new WorkspaceResponseDto(
                workspace.getId(),
                loginUser.getId(),
                workspace.getTitle(),
                workspace.getSubTitle()
        );
    }

    // 워크스페이스 단건 조회.
    public WorkspaceResponseDto findWorkspace(Long id) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionType.WORKSPACE_NOT_FOUND));

        Member member  = memberRepository.findByWorkspace_Id(workspace.getId());

        return new WorkspaceResponseDto(workspace, member.getUser().getId());
    }

    // 워크스페이스 수정
    @Transactional
    public WorkspaceResponseDto update(Long id, WorkspaceRequestDto dto) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionType.WORKSPACE_NOT_FOUND));

        workspace.updateTitle(dto.getTitle());
        workspace.updateSubTitle(dto.getSubTitle());

        Member member  = memberRepository.findByWorkspace_Id(workspace.getId());

        return new WorkspaceResponseDto(workspace, member.getUser().getId());
    }

    // 워크스페이스 삭제
    @Transactional
    public void deleteWorkspace(Long workspaceId) {

        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new CustomException(ExceptionType.DELETED_USER));
        workspaceRepository.delete(workspace);
    }
}
