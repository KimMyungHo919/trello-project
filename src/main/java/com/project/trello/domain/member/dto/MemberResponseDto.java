package com.project.trello.domain.member.dto;

import com.project.trello.global.enums.MemberRole;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final Long userID;
    private final Long workspaceId;
    private final MemberRole memberRole;

    public MemberResponseDto(Long userId, Long workspaceId, MemberRole memberRole) {
        this.userID = userId;
        this.workspaceId = workspaceId;
        this.memberRole = memberRole;
    }

}
