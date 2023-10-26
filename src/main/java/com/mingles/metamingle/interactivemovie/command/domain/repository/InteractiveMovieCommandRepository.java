package com.mingles.metamingle.interactivemovie.command.domain.repository;

import com.mingles.metamingle.interactivemovie.command.domain.aggregate.entity.InteractiveMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractiveMovieCommandRepository extends JpaRepository<InteractiveMovie, Long> {
}
