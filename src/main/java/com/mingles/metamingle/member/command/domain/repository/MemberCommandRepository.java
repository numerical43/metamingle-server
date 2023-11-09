package com.mingles.metamingle.member.command.domain.repository;

import com.mingles.metamingle.member.command.domain.aggregate.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCommandRepository extends JpaRepository<Member, Long>{

    boolean existsByEmail(String email);

}