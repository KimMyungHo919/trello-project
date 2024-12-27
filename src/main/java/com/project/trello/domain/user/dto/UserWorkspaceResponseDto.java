package com.project.trello.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserWorkspaceResponseDto {

    private Long workspaceId;
    private String workspaceTitle;
    private String workspaceSubTitle;
    private String workspaceRole;
}
