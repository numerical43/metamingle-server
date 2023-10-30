package com.mingles.metamingle.shortformlike.query.application.service;

import com.mingles.metamingle.shortformlike.command.domain.aggregate.entity.ShortFormLike;
import com.mingles.metamingle.shortformlike.command.domain.aggregate.vo.ShortFormLikeVO;
import com.mingles.metamingle.shortformlike.query.application.dto.response.CountShortFormLikeResponse;
import com.mingles.metamingle.shortformlike.query.application.dto.response.GetShortFormLikeResponse;
import com.mingles.metamingle.shortformlike.query.domain.repository.ShortFormLikeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortFormLikeQueryService {

    private final ShortFormLikeQueryRepository shortFormLikeQueryRepository;

    @Transactional
    public CountShortFormLikeResponse countShortFormLike(Long shortFormNo) {

        int likeCnt = shortFormLikeQueryRepository.countAllByShortFormLikeVO_ShortFormNo(shortFormNo);

        return new CountShortFormLikeResponse(likeCnt);

    }

    public GetShortFormLikeResponse getShortFormLike(Long memberNo, Long shortFormNo) {

        ShortFormLikeVO shortFormLikeVO = new ShortFormLikeVO(memberNo, shortFormNo);

        Optional<ShortFormLike> optionalShortFormLike = shortFormLikeQueryRepository.findShortFormLikeByShortFormLikeVO(shortFormLikeVO);

        if (optionalShortFormLike.isPresent()) {
            return new GetShortFormLikeResponse(true);
        }

        return new GetShortFormLikeResponse(false);
    }
}
