
package com.mingles.metamingle.member.query.infrastructure.repository;

import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberQueryRepository extends JpaRepository<Member, Long> {
}