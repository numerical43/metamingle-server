package com.mingles.metamingle.interativemovie.command.domain.repository;

import com.mingles.metamingle.interativemovie.command.domain.aggregate.entity.InteractiveMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractiveMovieCommandRepository extends JpaRepository<InteractiveMovie, Long> {
}
