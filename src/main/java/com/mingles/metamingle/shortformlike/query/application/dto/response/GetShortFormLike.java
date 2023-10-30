package com.mingles.metamingle.shortformlike.query.application.dto.response;

import lombok.Getter;

@Getter
public class GetShortFormLike {

    private final Boolean isLike;

    public GetShortFormLike(Boolean isLike) {
        this.isLike = isLike;
    }

}
