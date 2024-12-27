package com.project.trello.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberRequestDto {

    private final Long userID;
    private final Long workspaceId;

    public MemberRequestDto(Long userId, Long workspaceId) {
        this.userID = userId;
        this.workspaceId = workspaceId;
    }

}
