package com.mingles.metamingle.shortformlike.query.application.dto.response;

import lombok.Getter;

@Getter
public class GetShortFormLike {

    private final Boolean isLike;
    private final int likeCnt;

    public GetShortFormLike(Boolean isLike, int likeCnt) {
        this.isLike = isLike;
        this.likeCnt = likeCnt;
    }

}
