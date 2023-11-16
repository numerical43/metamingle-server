
package com.mingles.metamingle.avatar.query.infrastructure.repository;

import com.mingles.metamingle.avatar.command.domain.aggregate.entity.Avatar;
import com.mingles.metamingle.avatar.command.domain.aggregate.vo.AvatarMemberNoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarQueryRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByAvatarMemberNoVO(AvatarMemberNoVO memberNoVO);
}