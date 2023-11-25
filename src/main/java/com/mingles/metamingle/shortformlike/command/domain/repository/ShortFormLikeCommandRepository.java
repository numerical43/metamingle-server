package com.mingles.metamingle.shortformlike.command.domain.repository;

import com.mingles.metamingle.shortformlike.command.domain.aggregate.entity.ShortFormLike;
import com.mingles.metamingle.shortformlike.command.domain.aggregate.vo.ShortFormLikeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShortFormLikeCommandRepository extends JpaRepository<ShortFormLike, ShortFormLikeVO> {

    Optional<ShortFormLike> findShortFormLikeByShortFormLikeVO(ShortFormLikeVO shortFormLikeVO);



}
