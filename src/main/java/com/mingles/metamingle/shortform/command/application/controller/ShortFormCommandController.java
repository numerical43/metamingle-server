package com.mingles.metamingle.shortform.command.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.common.ApiStatus;
import com.mingles.metamingle.shortform.command.application.dto.request.ShortFormCreateRequest;
import com.mingles.metamingle.shortform.command.application.dto.response.ShortFormCreateResponse;
import com.mingles.metamingle.shortform.command.application.service.ShortFormCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ShortFormCommandController {

    private final ShortFormCommandService shortFormCommandService;

    // 숏폼 생성
    @PostMapping("/short-form")
    public ApiResponse createShortForm(@RequestPart MultipartFile file, @RequestPart ShortFormCreateRequest request) {

        ShortFormCreateResponse shortFormUrl = null;

        return new ApiResponse(ApiStatus.SUCCESS, "동영상 저장 성공", shortFormUrl);

    }

    // 숏폼 삭제

    // 숏폼 수정

}
