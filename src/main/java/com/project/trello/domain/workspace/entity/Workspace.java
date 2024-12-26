package com.project.trello.domain.workspace.entity;

import com.project.trello.domain.member.entity.Member;
import com.project.trello.global.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Workspace extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // 워크스페이스 이름
    private String subtitle; // 워크스페이스 부제목

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    private Workspace(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }
}
