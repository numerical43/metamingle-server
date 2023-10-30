package com.mingles.metamingle.movielike.query.domain.repository;

import com.mingles.metamingle.movielike.command.domain.aggregate.entity.InteractiveMovieLike;
import com.mingles.metamingle.movielike.command.domain.aggregate.vo.InteractiveMovieLikeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractiveMovieLikeQueryRepository extends JpaRepository<InteractiveMovieLike, InteractiveMovieLikeVO> {

    int countAllByInteractiveMovieLikeVO_InteractiveMovieNo(Long interactiveMovieNo);

    InteractiveMovieLike findInteractiveMovieLikeByInteractiveMovieLikeVO(InteractiveMovieLikeVO interactiveMovieLikeVO);
}
