package com.mingles.metamingle.movielike.command.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.movielike.command.application.dto.response.CreateInteractiveMovieLikeResponse;
import com.mingles.metamingle.movielike.command.application.service.InteractiveMovieLikeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InteractiveMovieLIkeCommandController {

    private final InteractiveMovieLikeCommandService interactiveMovieLikeCommandService;

    @PostMapping("/interactive-movie/{interactiveMovieNo}/like")
    public ResponseEntity<ApiResponse> createOrUpdateInteractiveMovieLike(@PathVariable("interactiveMovieNo") Long interactiveMovieNo) {
        // 헤더에서 토큰으로 memberNo 가져오기
        Long memberNo = 1L;
        CreateInteractiveMovieLikeResponse response = interactiveMovieLikeCommandService.createOrUpdateInteractiveMovieLike(memberNo, interactiveMovieNo);

        return ResponseEntity.ok(ApiResponse.success("좋아요 성공", response));
    }

}
