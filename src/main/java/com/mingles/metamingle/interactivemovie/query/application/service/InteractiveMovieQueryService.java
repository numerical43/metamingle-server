package com.mingles.metamingle.interactivemovie.query.application.service;

import com.mingles.metamingle.interactivemovie.command.domain.aggregate.entity.InteractiveMovie;
import com.mingles.metamingle.interactivemovie.query.application.dto.response.GetInteractiveMovieResponse;
import com.mingles.metamingle.interactivemovie.query.domain.repository.InteractiveMovieQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InteractiveMovieQueryService {

    private final InteractiveMovieQueryRepository interactiveMovieQueryRepository;

    public GetInteractiveMovieResponse getInteractiveMovie(Long interactiveMovieNo) {

        InteractiveMovie interactiveMovie = interactiveMovieQueryRepository.findInteractiveMovieByInteractiveMovieNo(interactiveMovieNo);

        GetInteractiveMovieResponse response =
                new GetInteractiveMovieResponse(
                        interactiveMovie.getInteractiveMovieNo(),
                        interactiveMovie.getTitle(),
                        interactiveMovie.getThumbnailUrl(),
                        interactiveMovie.getUrl(),
                        interactiveMovie.getDescription(),
                "memberName",
                        interactiveMovie.getDate(),
                        interactiveMovie.getSequence());

        return response;
    }
}
