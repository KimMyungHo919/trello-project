package com.project.trello.domain.user.entity;

import com.project.trello.domain.config.BaseTimeEntity;
import com.project.trello.domain.enums.UserRole;
import com.project.trello.domain.enums.UserStatus;
import com.project.trello.global.customException.CustomException;
import com.project.trello.global.customException.ExceptionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

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
