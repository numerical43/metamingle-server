package com.mingles.metamingle.avatar.command.domain.repository;

import com.mingles.metamingle.avatar.command.domain.aggregate.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AvatarCommandRepository extends JpaRepository<Avatar, Long> {

}