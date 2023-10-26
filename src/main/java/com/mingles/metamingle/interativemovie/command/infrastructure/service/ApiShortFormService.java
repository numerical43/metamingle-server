package com.mingles.metamingle.interativemovie.command.infrastructure.service;

import com.mingles.metamingle.shortform.command.application.service.ShortFormFirebaseService;
import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
import lombok.RequiredArgsConstructor;
import org.jcodec.api.JCodecException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ApiShortFormService {

    private final ShortFormFirebaseService shortFormFirebaseService;

    public ShortForm createShortFormWithInteractiveMovie(MultipartFile file, String title, String description)
            throws JCodecException, IOException {

        return shortFormFirebaseService.createShortFormWithInteractiveMovie(file, title, description);

    }
}
