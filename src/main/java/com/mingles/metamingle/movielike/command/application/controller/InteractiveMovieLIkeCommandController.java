package com.mingles.metamingle.movielike.command.application.controller;

import com.mingles.metamingle.auth.JwtTokenProvider;
import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.movielike.command.application.dto.response.CreateInteractiveMovieLikeResponse;
import com.mingles.metamingle.movielike.command.application.service.InteractiveMovieLikeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InteractiveMovieLIkeCommandController {

    private final InteractiveMovieLikeCommandService interactiveMovieLikeCommandService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/interactive-movie/{interactiveMovieNo}/like")
    public ResponseEntity<ApiResponse> createOrUpdateInteractiveMovieLike(@RequestHeader("Authentication") String token, @PathVariable("interactiveMovieNo") Long interactiveMovieNo) {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);
        CreateInteractiveMovieLikeResponse response = interactiveMovieLikeCommandService.createOrUpdateInteractiveMovieLike(memberNo, interactiveMovieNo);

        return ResponseEntity.ok(ApiResponse.success("좋아요 성공", response));
    }

}
