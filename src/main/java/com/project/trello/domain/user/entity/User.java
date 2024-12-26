package com.project.trello.domain.user.entity;

import com.project.trello.domain.config.BaseTimeEntity;
import com.project.trello.domain.enums.UserRole;
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

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = UserRole.of(role);
    }
}
