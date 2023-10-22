package com.mingles.metamingle.interativemovie.query.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.common.ApiStatus;
import com.mingles.metamingle.interativemovie.query.application.dto.response.GetInteractiveMovieResponse;
import com.mingles.metamingle.interativemovie.query.application.service.InteractiveMovieQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InteractiveMovieQueryController {

    private final InteractiveMovieQueryService interactiveMovieQueryService;

    @GetMapping("/interactive-movie/{interactiveMovieNo}")
    public ResponseEntity<ApiResponse> getInteractiveMovie(@PathVariable("interactiveMovieNo") Long interactiveMovieNo) {

        GetInteractiveMovieResponse response = interactiveMovieQueryService.getInteractiveMovie(interactiveMovieNo);

        return ResponseEntity.ok(new ApiResponse(ApiStatus.SUCCESS, "인터랙티브 무비 조회 성공", response));

    }

}
