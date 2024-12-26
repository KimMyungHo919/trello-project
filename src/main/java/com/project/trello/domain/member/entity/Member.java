package com.project.trello.domain.member.entity;

import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.workspace.entity.Workspace;
import com.project.trello.global.config.BaseTimeEntity;
import com.project.trello.global.enums.MemberRole;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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
    private MemberRole memberRole; // ROLE_MEMBER, ROLE_ADMIN

}
