package com.mingles.metamingle.interactivemovie.command.infrastructure.service;

import com.mingles.metamingle.auth.JwtTokenProvider;
import com.mingles.metamingle.shortform.command.application.service.ShortFormFirebaseService;
import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
import lombok.RequiredArgsConstructor;
import org.jcodec.api.JCodecException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ApiShortFormService {

    private final ShortFormFirebaseService shortFormFirebaseService;
    private final JwtTokenProvider jwtTokenProvider;

    public ShortForm createShortFormWithInteractiveMovie(@RequestHeader("Authorization") String token, MultipartFile file, String title, String description)
            throws JCodecException, IOException {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);

        return shortFormFirebaseService.createShortFormWithInteractiveMovie(file, title, description, memberNo);

    }
}
