package com.mingles.metamingle.interativemovie.command.application.service;

import com.mingles.metamingle.interativemovie.command.application.dto.response.CreateInteractiveMovieResponse;
import com.mingles.metamingle.interativemovie.command.domain.repository.InteractiveMovieCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class InteractiveMovieCommandService {

    private final InteractiveMovieCommandRepository interactiveMovieCommandRepository;

    public CreateInteractiveMovieResponse createInteractiveMovie(MultipartFile[] videos, String[] titles, String[] descriptions) {

        return null;
    }
}
