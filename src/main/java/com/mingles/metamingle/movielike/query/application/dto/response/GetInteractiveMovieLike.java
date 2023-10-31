package com.mingles.metamingle.movielike.query.application.dto.response;

import lombok.Getter;

@Getter
public class GetInteractiveMovieLike {

    private final Boolean isLike;
    private final int likeCnt;

    public GetInteractiveMovieLike(Boolean isLike, int likeCnt) {
        this.isLike = isLike;
        this.likeCnt = likeCnt;
    }
}
