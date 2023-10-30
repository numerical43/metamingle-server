package com.mingles.metamingle.movielike.query.application.dto.response;

import lombok.Getter;

@Getter
public class CountInteractiveMovieLike {

    private final int likeCnt;

    public CountInteractiveMovieLike(int likeCnt) {
        this.likeCnt = likeCnt;
    }
}
