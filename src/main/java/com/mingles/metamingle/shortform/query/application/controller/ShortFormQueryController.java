package com.mingles.metamingle.shortform.query.application.controller;

import com.mingles.metamingle.auth.JwtTokenProvider;
import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormListResponse;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormResponse;
import com.mingles.metamingle.shortform.query.application.service.ShortFormQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShortFormQueryController {

    private final ShortFormQueryService shortFormQueryService;
    private final JwtTokenProvider jwtTokenProvider;

    // 숏폼 리스트 조회
    @GetMapping("/short-form")
    public ResponseEntity<ApiResponse> getShortFormList(@RequestHeader("Authentication") String token) {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);
//        Long memberNo = 1L;

        List<GetShortFormListResponse> response = shortFormQueryService.getShortFormList();

        return ResponseEntity.ok(ApiResponse.success("숏폼 리스트 조회 성공", response));
    }

    // 숏폼 하나 상세 조회
    @GetMapping("/short-form/{shortFormNo}")
    public ResponseEntity<ApiResponse> getShortForm(@RequestHeader("Authentication") String token, @PathVariable("shortFormNo") Long shortFormNo) {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);
//        Long memberNo = 1L;

        GetShortFormResponse response = shortFormQueryService.getShortForm(shortFormNo);

        return ResponseEntity.ok(ApiResponse.success("숏폼 상세 조회 성공", response));

    }
}
