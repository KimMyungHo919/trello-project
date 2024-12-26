package com.project.trello.domain.workspace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class WorkspaceRequestDto {

    @NotBlank
    @Size(max = 20)
    private final String title;

    @NotBlank
    @Size(max = 20)
    private final String subTitle;

    public WorkspaceRequestDto(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }
}
