package com.project.trello.domain.member.repository;

import com.project.trello.domain.member.entity.Member;
import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.workspace.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByWorkspace_Id(Long id);

    Optional<Member> findByUserAndWorkspace(User user, Workspace workspace);
}
