package com.mingles.metamingle.movielike.command.domain.repository;

import com.mingles.metamingle.movielike.command.domain.aggregate.entity.InteractiveMovieLike;
import com.mingles.metamingle.movielike.command.domain.aggregate.vo.InteractiveMovieLikeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InteractiveMovieLikeCommandRepository extends JpaRepository<InteractiveMovieLike, InteractiveMovieLikeVO> {

    Optional<InteractiveMovieLike> findInteractiveMovieLikeByInteractiveMovieLikeVO(InteractiveMovieLikeVO interactiveMovieLikeVO);
}
