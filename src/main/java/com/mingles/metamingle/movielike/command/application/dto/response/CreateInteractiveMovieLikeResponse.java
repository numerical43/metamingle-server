package com.mingles.metamingle.movielike.command.application.dto.response;

import lombok.Getter;

@Getter
public class CreateInteractiveMovieLikeResponse {

    private final boolean like;

    public CreateInteractiveMovieLikeResponse(boolean like) {
        this.like = like;
    }

}
