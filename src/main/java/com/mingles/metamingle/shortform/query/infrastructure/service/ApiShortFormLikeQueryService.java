package com.mingles.metamingle.shortform.query.infrastructure.service;

import com.mingles.metamingle.shortform.query.application.dto.response.ShortFormLikeInfoDTO;
import com.mingles.metamingle.shortformlike.query.application.dto.response.GetShortFormLike;
import com.mingles.metamingle.shortformlike.query.application.service.ShortFormLikeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiShortFormLikeQueryService {

    private final ShortFormLikeQueryService shortFormLikeQueryService;


    public ShortFormLikeInfoDTO getShortFormLikeInfo(Long memberNo, Long shortFormNo) {

        GetShortFormLike getShortFormLike = shortFormLikeQueryService.getShortFormLike(memberNo, shortFormNo);

        return new ShortFormLikeInfoDTO(getShortFormLike.getLikeCnt(), getShortFormLike.getIsLike());
    }
}
