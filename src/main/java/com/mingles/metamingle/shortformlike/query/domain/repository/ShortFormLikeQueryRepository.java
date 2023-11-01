package com.mingles.metamingle.shortformlike.query.domain.repository;


import com.mingles.metamingle.shortformlike.command.domain.aggregate.entity.ShortFormLike;
import com.mingles.metamingle.shortformlike.command.domain.aggregate.vo.ShortFormLikeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortFormLikeQueryRepository extends JpaRepository<ShortFormLike, ShortFormLikeVO> {

    int countAllByShortFormLikeVO_ShortFormNo(Long shortFormNo);

    Optional<ShortFormLike> findShortFormLikeByShortFormLikeVO(ShortFormLikeVO shortFormLikeVO);

}
