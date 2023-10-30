package com.mingles.metamingle.shortformlike.query.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.shortformlike.query.application.dto.response.CountShortFormLikeResponse;
import com.mingles.metamingle.shortformlike.query.application.dto.response.GetShortFormLikeResponse;
import com.mingles.metamingle.shortformlike.query.application.service.ShortFormLikeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortFormLikeQueryController {

    private final ShortFormLikeQueryService shortFormLikeQueryService;

    @GetMapping("/short-form/{shortFormNo}/like")
    public ResponseEntity<ApiResponse> countShortFormLike(@PathVariable("shortFormNo") Long shortFormNo) {

        CountShortFormLikeResponse response = shortFormLikeQueryService.countShortFormLike(shortFormNo);

        return ResponseEntity.ok(ApiResponse.success("좋아요 수 조회 성공", response));
    }

    @GetMapping("/short-form/{shortFormNo}/is-like")
    public ResponseEntity<ApiResponse> getShortFormLike(@PathVariable("shortFormNo") Long shortFormNo) {

        Long memberNo = 1L;

        GetShortFormLikeResponse response = shortFormLikeQueryService.getShortFormLike(memberNo, shortFormNo);

        return ResponseEntity.ok(ApiResponse.success("숏폼 isLike 조회 성공", response));
    }
}
