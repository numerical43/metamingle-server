package com.mingles.metamingle.movielike.query.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountInteractiveMovieLike {

    private int likeCnt;

    public CountInteractiveMovieLike(int likeCnt) {
        this.likeCnt = likeCnt;
    }
}
