package com.project.trello.domain.workspace.entity;

import com.project.trello.domain.member.entity.Member;
import com.project.trello.global.config.BaseTimeEntity;
import com.project.trello.global.customException.CustomException;
import com.project.trello.global.customException.ExceptionType;
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
    private String subTitle; // 워크스페이스 부제목

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    public Workspace(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public void updateTitle(String title) {
        if (this.title.equals(title)) {
            throw new CustomException(ExceptionType.TITLE_SAME);
        }
        this.title = title;
    }

    public void updateSubTitle(String subTitle) {
        if (this.subTitle.equals(subTitle)) {
            throw new CustomException(ExceptionType.SUBTITLE_SAME);
        }
        this.subTitle = subTitle;
    }
}
