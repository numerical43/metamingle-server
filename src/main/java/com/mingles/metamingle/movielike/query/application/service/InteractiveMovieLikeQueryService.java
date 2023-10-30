package com.mingles.metamingle.movielike.query.application.service;

import com.mingles.metamingle.movielike.command.domain.aggregate.entity.InteractiveMovieLike;
import com.mingles.metamingle.movielike.command.domain.aggregate.vo.InteractiveMovieLikeVO;
import com.mingles.metamingle.movielike.query.application.dto.response.CountInteractiveMovieLike;
import com.mingles.metamingle.movielike.query.application.dto.response.GetInteractiveMovieLike;
import com.mingles.metamingle.movielike.query.domain.repository.InteractiveMovieLikeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InteractiveMovieLikeQueryService {

    private final InteractiveMovieLikeQueryRepository interactiveMovieLikeQueryRepository;


    @Transactional(readOnly = true)
    public CountInteractiveMovieLike countInteractiveMovieLike(Long interactiveMovieNo) {

        int likeCnt = interactiveMovieLikeQueryRepository.countAllByInteractiveMovieLikeVO_InteractiveMovieNo(interactiveMovieNo);

        return new CountInteractiveMovieLike(likeCnt);

    }

    @Transactional(readOnly = true)
    public GetInteractiveMovieLike getInteractiveMovieLike(Long memberNo, Long interactiveMovieNo) {

        InteractiveMovieLikeVO interactiveMovieLikeVO = new InteractiveMovieLikeVO(interactiveMovieNo, memberNo);

        Optional<InteractiveMovieLike> optionalInteractiveMovieLike = interactiveMovieLikeQueryRepository
                .findInteractiveMovieLikeByInteractiveMovieLikeVO(interactiveMovieLikeVO);

        if (optionalInteractiveMovieLike.isPresent()) {
            return new GetInteractiveMovieLike(true);
        }

        return new GetInteractiveMovieLike(false);

    }
}
