package com.mingles.metamingle.movielike.query.application.service;

import com.mingles.metamingle.movielike.query.application.dto.response.CountInteractiveMovieLike;
import com.mingles.metamingle.movielike.query.domain.repository.InteractiveMovieLikeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InteractiveMovieLikeQueryService {

    private final InteractiveMovieLikeQueryRepository interactiveMovieLikeQueryRepository;


    public CountInteractiveMovieLike getInteractiveMovieLike(Long interactiveMovieNo) {

        int likeCnt = interactiveMovieLikeQueryRepository.countAllByInteractiveMovieLikeVO_InteractiveMovieNo(interactiveMovieNo);

        return new CountInteractiveMovieLike(likeCnt);

    }
}
