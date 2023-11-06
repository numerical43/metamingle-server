package com.mingles.metamingle.shortform.query.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortFormLikeInfoDTO {
    private int shortFormLikeCnt;
    private Boolean isLike;

    public ShortFormLikeInfoDTO(int shortFormLikeCnt, Boolean isLike) {
        this.shortFormLikeCnt = shortFormLikeCnt;
        this.isLike = isLike;
    }
}
