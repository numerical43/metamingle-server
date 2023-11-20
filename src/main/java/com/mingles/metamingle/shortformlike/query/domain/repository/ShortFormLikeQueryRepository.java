package com.mingles.metamingle.shortformlike.query.domain.repository;


import com.mingles.metamingle.shortformlike.command.domain.aggregate.entity.ShortFormLike;
import com.mingles.metamingle.shortformlike.command.domain.aggregate.vo.ShortFormLikeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShortFormLikeQueryRepository extends JpaRepository<ShortFormLike, ShortFormLikeVO> {

    int countAllByShortFormLikeVO_ShortFormNo(Long shortFormNo);

    Optional<ShortFormLike> findShortFormLikeByShortFormLikeVO(ShortFormLikeVO shortFormLikeVO);

    @Query("SELECT sflike.shortFormLikeVO.shortFormNo, COUNT(sflike) as likeCount " +
            "FROM ShortFormLike sflike " +
            "GROUP BY sflike.shortFormLikeVO.shortFormNo " +
            "ORDER BY likeCount DESC ")
    List<Object[]> findTop12LikedShortForms();
}
