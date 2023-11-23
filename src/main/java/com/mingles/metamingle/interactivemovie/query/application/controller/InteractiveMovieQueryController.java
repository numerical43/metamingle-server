package com.mingles.metamingle.interactivemovie.query.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.common.ApiStatus;
import com.mingles.metamingle.interactivemovie.query.application.dto.response.GetInteractiveMovieResponse;
import com.mingles.metamingle.interactivemovie.query.application.service.InteractiveMovieQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class InteractiveMovieQueryController {

    private final InteractiveMovieQueryService interactiveMovieQueryService;

    @GetMapping("/interactive-movie/{interactiveMovieNo}")
    public ResponseEntity<ApiResponse> getInteractiveMovie(@RequestHeader("Authentication") String token,
                                                           @PathVariable("interactiveMovieNo") Long interactiveMovieNo,
                                                           @RequestParam(name = "language", defaultValue = "kr") String language) {

        GetInteractiveMovieResponse response = interactiveMovieQueryService.getInteractiveMovie(interactiveMovieNo, language);

        return ResponseEntity.ok(new ApiResponse(ApiStatus.SUCCESS, "인터랙티브 무비 조회 성공", response));

    }

}
