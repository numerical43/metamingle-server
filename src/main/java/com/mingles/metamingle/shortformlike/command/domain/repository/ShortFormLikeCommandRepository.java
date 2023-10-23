package com.mingles.metamingle.shortformlike.command.domain.repository;

import com.mingles.metamingle.shortformlike.command.domain.aggregate.entity.ShortFormLike;
import com.mingles.metamingle.shortformlike.command.domain.aggregate.vo.ShortFormLikeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortFormLikeCommandRepository extends JpaRepository<ShortFormLike, ShortFormLikeVO> {



}
