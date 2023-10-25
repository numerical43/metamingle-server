package com.mingles.metamingle.shortformlike.command.application.service;

import com.mingles.metamingle.shortformlike.command.application.dto.response.CreateShortFormLikeResponse;
import com.mingles.metamingle.shortformlike.command.domain.aggregate.entity.ShortFormLike;
import com.mingles.metamingle.shortformlike.command.domain.aggregate.vo.ShortFormLikeVO;
import com.mingles.metamingle.shortformlike.command.domain.repository.ShortFormLikeCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortFormLikeCommandService {

    private final ShortFormLikeCommandRepository shortFormLikeCommandRepository;


    @Transactional
    public CreateShortFormLikeResponse createShortFormLike(Long memberNo, Long shortFormNo) {

        ShortFormLikeVO shortFormLikeVO = new ShortFormLikeVO(memberNo, shortFormNo);

        Optional<ShortFormLike> optionalShortFormLike = shortFormLikeCommandRepository.findShortFormLikeByShortFormLikeVO(shortFormLikeVO);

        CreateShortFormLikeResponse response = new CreateShortFormLikeResponse();

        if (optionalShortFormLike.isEmpty()) {

            ShortFormLike shortFormLike = new ShortFormLike(shortFormLikeVO);
            shortFormLikeCommandRepository.save(shortFormLike);

            response.setLike(true);

            return response;

        }

        shortFormLikeCommandRepository.delete(optionalShortFormLike.get());

        response.setLike(false);

        return response;
    }
}
