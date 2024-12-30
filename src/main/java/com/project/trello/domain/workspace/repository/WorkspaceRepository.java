package com.project.trello.domain.workspace.repository;

import com.project.trello.domain.workspace.entity.Workspace;
import com.project.trello.global.customException.CustomException;
import com.project.trello.global.customException.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    default Workspace findByWorkspaceId(Long workspaceId) {
        return findById(workspaceId).orElseThrow(() -> new CustomException(ExceptionType.WORKSPACE_NOT_FOUND));
    }
}
