package com.mingles.metamingle.movielike.command.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInteractiveMovieLikeResponse {

    private boolean like;

    public CreateInteractiveMovieLikeResponse(boolean like) {
        this.like = like;
    }

}
