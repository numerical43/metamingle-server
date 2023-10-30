package com.mingles.metamingle.movielike.query.application.dto.response;

import lombok.Getter;

@Getter
public class GetInteractiveMovieLike {

    private final Boolean isLike;

    public GetInteractiveMovieLike(Boolean isLike) {
        this.isLike = isLike;
    }
}
