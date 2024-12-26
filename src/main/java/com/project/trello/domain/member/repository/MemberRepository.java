package com.project.trello.domain.member.repository;

import com.project.trello.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByWorkspace_Id(Long id);
}
