package com.mingles.metamingle.shortformlike.query.application.controller;

import com.mingles.metamingle.global.auth.JwtTokenProvider;
import com.mingles.metamingle.global.common.ApiResponse;
import com.mingles.metamingle.shortformlike.query.application.dto.response.CountShortFormLike;
import com.mingles.metamingle.shortformlike.query.application.dto.response.GetShortFormLike;
import com.mingles.metamingle.shortformlike.query.application.service.ShortFormLikeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortFormLikeQueryController {

    private final ShortFormLikeQueryService shortFormLikeQueryService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/short-form/{shortFormNo}/like")
    public ResponseEntity<ApiResponse> countShortFormLike(@RequestHeader("Authentication") String token,
                                                          @PathVariable("shortFormNo") Long shortFormNo) {

        CountShortFormLike response = shortFormLikeQueryService.countShortFormLike(shortFormNo);

        return ResponseEntity.ok(ApiResponse.success("좋아요 수 조회 성공", response));
    }

    @GetMapping("/short-form/{shortFormNo}/is-like")
    public ResponseEntity<ApiResponse> getShortFormLike(@RequestHeader("Authentication") String token,
                                                        @PathVariable("shortFormNo") Long shortFormNo) {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);

        GetShortFormLike response = shortFormLikeQueryService.getShortFormLike(memberNo, shortFormNo);

        return ResponseEntity.ok(ApiResponse.success("숏폼 좋아요 isLike 조회 성공", response));
    }
}
