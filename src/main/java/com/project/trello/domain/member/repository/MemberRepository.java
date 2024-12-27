package com.project.trello.domain.member.repository;

import com.project.trello.domain.member.entity.Member;
import com.project.trello.domain.user.entity.User;
import com.project.trello.domain.workspace.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByWorkspace_Id(Long id);

    Optional<Member> findByUserAndWorkspace(User user, Workspace workspace);

    Optional<Member> findByUser_IdAndWorkspace_Id(Long userid, Long workspaceId);

    List<Member> findAllById(Long loginUserId);
}
