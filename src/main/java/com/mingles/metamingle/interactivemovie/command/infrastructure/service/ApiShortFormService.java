package com.mingles.metamingle.interactivemovie.command.infrastructure.service;

import com.mingles.metamingle.shortform.command.application.dto.response.CreateShortFormResponse;
import com.mingles.metamingle.shortform.command.application.service.ShortFormFirebaseService;
import lombok.RequiredArgsConstructor;
import org.jcodec.api.JCodecException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ApiShortFormService {

    private final ShortFormFirebaseService shortFormFirebaseService;

    public CreateShortFormResponse createShortFormWithInteractiveMovie(byte[] file, String fileName, String uuid,
                                                                       String title, String description,
                                                                       Long memberNo, Boolean isInteractive)
                                                                       throws JCodecException, IOException {

        return shortFormFirebaseService.createShortFormWithSubtitle(file, fileName, uuid, title,
                                                                    description, memberNo, isInteractive);
    }
}
