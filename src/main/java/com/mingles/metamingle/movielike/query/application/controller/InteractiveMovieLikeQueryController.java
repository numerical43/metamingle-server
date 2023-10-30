package com.mingles.metamingle.movielike.query.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.movielike.query.application.dto.response.CountInteractiveMovieLike;
import com.mingles.metamingle.movielike.query.application.dto.response.GetInteractiveMovieLike;
import com.mingles.metamingle.movielike.query.application.service.InteractiveMovieLikeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InteractiveMovieLikeQueryController {

    private final InteractiveMovieLikeQueryService interactiveMovieLikeQueryService;

    @GetMapping("/interactive-movie/{interactiveMovieNo}/like")
    public ResponseEntity<ApiResponse> getInteractiveMovieLikeCount(@PathVariable("interactiveMovieNo") Long interactiveMovieNo) {

        CountInteractiveMovieLike response = interactiveMovieLikeQueryService.countInteractiveMovieLike(interactiveMovieNo);

        return ResponseEntity.ok(ApiResponse.success("좋아요 수 조회 성공", response));
    }

    @GetMapping("/interactive-movie/{interactiveMovieNo}/is-like")
    public ResponseEntity<ApiResponse> getInteractiveMovieLike(@PathVariable("interactiveMovieNo") Long interactiveMovieNo) {
        // 헤더에서 멤버 정보
        Long memberNo = 1L;

        GetInteractiveMovieLike response = interactiveMovieLikeQueryService.getInteractiveMovieLike(memberNo, interactiveMovieNo);

        return ResponseEntity.ok(ApiResponse.success("인터랙티브 무비 좋아요 isLike 조회 성공", response));
    }
}

