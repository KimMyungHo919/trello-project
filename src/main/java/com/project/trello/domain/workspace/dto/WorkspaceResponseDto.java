package com.project.trello.domain.workspace.dto;

import lombok.Getter;

@Getter
public class WorkspaceResponseDto {

    private final Long id;
    private final Long createUserId;
    private final String title;
    private final String subTitle;

    public WorkspaceResponseDto(Long id, Long createUserId, String title, String subTitle) {
        this.id = id;
        this.createUserId = createUserId;
        this.title = title;
        this.subTitle = subTitle;
    }
}
