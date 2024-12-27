package com.project.trello.domain.member.service;

import com.project.trello.domain.member.dto.MemberRequestDto;
import com.project.trello.domain.member.dto.MemberResponseDto;
import com.project.trello.domain.member.entity.Member;
import com.project.trello.domain.member.repository.MemberRepository;
import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.user.repository.UserRepository;
import com.project.trello.domain.workspace.entity.Workspace;
import com.project.trello.domain.workspace.repository.WorkspaceRepository;
import com.project.trello.global.customException.CustomException;
import com.project.trello.global.customException.ExceptionType;
import com.project.trello.global.enums.MemberRole;
import com.project.trello.global.enums.UserRole;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;

    // dto 에 등록하고자하는 유저아이디, 워크스페이스아이디
    @Transactional
    public MemberResponseDto add(User loginUser, MemberRequestDto dto) {

        // 로그인유저가 권한이 있는지 확인. ADMIN권한이 없으면 에러처리. 있을경우 계속 진행
        if (!Objects.equals(UserRole.ROLE_ADMIN, loginUser.getRole())) {
            throw new CustomException(ExceptionType.ROLE_NOT_CORRECT);
        }

        // dto에 유저정보로 유저 조회. 없으면 에러.
        User user = userRepository.findById(dto.getUserID())
                .orElseThrow(() -> new CustomException(ExceptionType.USER_NOT_FOUND));

        // dto에 워크스페이스 아이디로 워크스페이스 조회. 없으면 에러.
        Workspace workspace = workspaceRepository.findById(dto.getWorkspaceId())
                .orElseThrow(() -> new CustomException(ExceptionType.WORKSPACE_NOT_FOUND));

        // 멤버생성 후 저장. 유저,워크스페이스,권한
        Member member = new Member(user, workspace, MemberRole.ROLE_MEMBER);

        // 중복처리.
        if (memberRepository.findByUserAndWorkspace(user, workspace).isPresent()) {
            throw new CustomException(ExceptionType.ALREADY_MEMBER);
        }

        memberRepository.save(member);

        return new MemberResponseDto(user.getId(), workspace.getId(), member.getMemberRole());
    }

    // 멤버삭제
    @Transactional
    public void deleteMember(User loginUser, MemberRequestDto dto) {
        // 로그인한 유저의 권한 확인.
        if (!Objects.equals(UserRole.ROLE_ADMIN, loginUser.getRole())) {
            throw new CustomException(ExceptionType.ROLE_NOT_CORRECT);
        }
        // dto로 멤버 찾아오기.
        Member member = memberRepository
                .findByUser_IdAndWorkspace_Id(dto.getUserID(), dto.getWorkspaceId())
                .orElseThrow(() -> new CustomException(ExceptionType.USER_NOT_FOUND));
        // 멤버 삭제.
        memberRepository.delete(member);
    }
}
