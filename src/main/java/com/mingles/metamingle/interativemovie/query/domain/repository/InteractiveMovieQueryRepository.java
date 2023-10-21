package com.mingles.metamingle.interativemovie.query.domain.repository;

import com.mingles.metamingle.interativemovie.command.domain.aggregate.entity.InteractiveMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractiveMovieQueryRepository extends JpaRepository<InteractiveMovie, Long> {

    InteractiveMovie findInteractiveMovieByInteractiveMovieNo(Long interactiveMovieNo);

}
