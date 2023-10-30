package com.mingles.metamingle.shortformlike.query.application.dto.response;

import lombok.Getter;

@Getter
public class GetShortFormLikeResponse {

    private final Boolean isLike;

    public GetShortFormLikeResponse(Boolean isLike) {
        this.isLike = isLike;
    }

}
