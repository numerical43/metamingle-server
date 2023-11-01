package com.mingles.metamingle.shortformlike.command.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.shortformlike.command.application.dto.response.CreateShortFormLikeResponse;
import com.mingles.metamingle.shortformlike.command.application.service.ShortFormLikeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortFormLikeCommandController {

    private final ShortFormLikeCommandService shortFormLikeCommandService;

    @PostMapping("/short-form/{shortFormNo}/like")
    public ResponseEntity<ApiResponse> createOrUpdateShortFormLike(@PathVariable("shortFormNo") Long shortFormNo) {
        // 헤더에서 토큰으로 memberNo 가져오기
        Long memberNo = 1L;
        CreateShortFormLikeResponse response = shortFormLikeCommandService.createShortFormLike(memberNo, shortFormNo);

        return ResponseEntity.ok(ApiResponse.success("좋아요 성공", response));
    }

}
