
package com.mingles.metamingle.member.query.infrastructure.repository;

import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberQueryRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByEmail(String email);
}