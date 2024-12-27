package com.project.trello.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.workspace.entity.Workspace;
import com.project.trello.global.config.BaseTimeEntity;
import com.project.trello.global.customException.CustomException;
import com.project.trello.global.customException.ExceptionType;
import com.project.trello.global.enums.MemberRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole; // ROLE_MEMBER, ROLE_ADMIN, ROLE_READ_ONLY

    public Member(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    public Member(User loginUser, Workspace workspace, MemberRole memberRole) {
        this.user = loginUser;
        this.workspace = workspace;
        this.memberRole = memberRole;
    }

    public void changeMemberRole(MemberRole memberRole) {
        if (Objects.equals(this.memberRole, memberRole)) {
            throw new CustomException(ExceptionType.CHANGE_MEMBER_ROLE);
        }
        this.memberRole = memberRole;
    }

    public void setUser(User user) {
        this.user = user;
        user.getMembers().add(this);
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
        workspace.getMembers().add(this);
    }

}
