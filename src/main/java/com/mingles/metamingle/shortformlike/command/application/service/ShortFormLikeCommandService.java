package com.mingles.metamingle.shortformlike.command.application.service;

import com.mingles.metamingle.shortformlike.command.application.dto.response.CreateShortFormLikeResponse;
import com.mingles.metamingle.shortformlike.command.domain.repository.ShortFormLikeCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShortFormLikeCommandService {

    private final ShortFormLikeCommandRepository shortFormLikeCommandRepository;


    @Transactional
    public CreateShortFormLikeResponse createShortFormLike(Long memberNo) {
        member
    }
}
