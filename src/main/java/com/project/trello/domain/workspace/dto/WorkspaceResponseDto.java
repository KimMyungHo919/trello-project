package com.project.trello.domain.workspace.dto;

import com.project.trello.domain.workspace.entity.Workspace;
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

    public WorkspaceResponseDto(Workspace workspace, Long createUserId) {
        this.id = workspace.getId();
        this.createUserId = createUserId;
        this.title = workspace.getTitle();
        this.subTitle = workspace.getSubTitle();
    }
}
