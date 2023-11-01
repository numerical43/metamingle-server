package com.mingles.metamingle.shortformlike.query.application.dto.response;

import lombok.Getter;

@Getter
public class CountShortFormLike {

    private final int likeCnt;

    public CountShortFormLike(int likeCnt) {
        this.likeCnt = likeCnt;
    }

}
