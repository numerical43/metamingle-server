package com.mingles.metamingle.interativemovie.command.domain.repository;

import com.mingles.metamingle.interativemovie.command.domain.aggregate.entity.InteractiveMovie;
import com.mingles.metamingle.interativemovie.command.domain.aggregate.vo.ShortFormNoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractiveMovieCommandRepository extends JpaRepository<InteractiveMovie, Long> {

    List<InteractiveMovie> findAllByShortFormNoVO(ShortFormNoVO shortFormNoVO);

}
