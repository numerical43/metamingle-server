package com.mingles.metamingle.shortformlike.command.application.dto.response;

import lombok.Getter;

@Getter
public class CreateShortFormLikeResponse {

    private final boolean like;

    public CreateShortFormLikeResponse(boolean like) {
        this.like = like;
    }

}
