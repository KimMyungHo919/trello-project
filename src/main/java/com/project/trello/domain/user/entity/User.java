package com.project.trello.domain.user.entity;

import com.project.trello.domain.member.entity.Member;
import com.project.trello.global.config.BaseTimeEntity;
import com.project.trello.global.enums.UserRole;
import com.project.trello.global.enums.UserStatus;
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
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "user")
    private List<Member> members = new ArrayList<>();

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = UserRole.of(role);
        this.status = UserStatus.ACTIVE;
    }

    public void userDeleted() {
        if (this.status == UserStatus.INACTIVE) {
            throw new CustomException(ExceptionType.DELETED_USER);
        }

        this.status = UserStatus.INACTIVE;
    }
}
