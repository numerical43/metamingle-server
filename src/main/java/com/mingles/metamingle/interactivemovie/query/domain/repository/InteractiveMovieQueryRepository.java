package com.mingles.metamingle.interactivemovie.query.domain.repository;

import com.mingles.metamingle.interactivemovie.command.domain.aggregate.entity.InteractiveMovie;
import com.mingles.metamingle.interactivemovie.command.domain.aggregate.vo.ShortFormNoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractiveMovieQueryRepository extends JpaRepository<InteractiveMovie, Long> {

    InteractiveMovie findInteractiveMovieByInteractiveMovieNo(Long interactiveMovieNo);

    List<InteractiveMovie> findInteractiveMoviesByShortFormNoVO(ShortFormNoVO shortFormNoVO);

}
