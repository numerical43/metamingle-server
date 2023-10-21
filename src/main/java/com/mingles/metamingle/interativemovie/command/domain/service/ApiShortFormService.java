package com.mingles.metamingle.interativemovie.command.domain.service;

import com.mingles.metamingle.shortform.command.application.service.ShortFormFirebaseService;
import com.mingles.metamingle.shortform.command.domain.repository.ShortFormCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ApiShortFormService {

    private final ShortFormFirebaseService shortFormFirebaseService;
    private final ShortFormCommandRepository shortFormCommandRepository;

//    public Long createShortFormWithInteractiveMovie(MultipartFile file, String title, String description) {
//
//    }
}
