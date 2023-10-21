package com.mingles.metamingle.interativemovie.command.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.common.ApiStatus;
import com.mingles.metamingle.interativemovie.command.application.dto.response.CreateInteractiveMovieResponse;
import com.mingles.metamingle.interativemovie.command.application.service.InteractiveMovieCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class InteractiveMovieCommandController {

    private final InteractiveMovieCommandService interactiveMovieCommandService;

    // 인터랙티브 무비 생성
    public ResponseEntity<ApiResponse> createInteractiveMovie(@RequestPart("video") MultipartFile[] videos,
                                                              @RequestPart("title") String[] titles,
                                                              @RequestPart("description") String[] descriptions) {

        CreateInteractiveMovieResponse response = interactiveMovieCommandService.createInteractiveMovie(videos, titles, descriptions);

        return ResponseEntity.ok(new ApiResponse(ApiStatus.SUCCESS, "인터랙티브 무비 생성 성공", response));
    }


}
