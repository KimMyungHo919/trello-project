package com.project.trello.domain.user.repository;

import com.project.trello.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select u from User u " +
            "join fetch u.members m " +
            "join fetch m.workspace " +
            "where u.id = :userId")
    Optional<User> findByIdWithMembersAndWorkspaces(Long userId);
}
