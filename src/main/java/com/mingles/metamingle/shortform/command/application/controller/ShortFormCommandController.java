package com.mingles.metamingle.shortform.command.application.controller;

import com.mingles.metamingle.global.auth.JwtTokenProvider;
import com.mingles.metamingle.global.common.ApiResponse;
import com.mingles.metamingle.shortform.command.application.dto.response.CreateShortFormResponse;
import com.mingles.metamingle.shortform.command.application.dto.response.DeleteShortFormResponse;
import com.mingles.metamingle.shortform.command.application.service.ShortFormFirebaseService;
import lombok.RequiredArgsConstructor;
import org.jcodec.api.JCodecException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ShortFormCommandController {

    private final ShortFormFirebaseService shortFormFirebaseService;
    private final JwtTokenProvider jwtTokenProvider;

    // 숏폼 생성
    @PostMapping("/short-form-firebase")
    public ResponseEntity<ApiResponse> createShortFormWithFirebase(@RequestHeader("Authentication") String token,
                                                                   @RequestPart("video") MultipartFile video,
                                                                   @RequestPart("title") String title,
                                                                   @RequestPart("description") String description,
                                                                   @RequestPart("uuid") String uuid) throws JCodecException, IOException {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);

        // 비동기 처리를 위해서 MultipartFile을 byte[]로 변환
        byte[] videoBytes = video.getBytes();
        String fileName = video.getOriginalFilename();

        CreateShortFormResponse response = shortFormFirebaseService.createShortForm(videoBytes, fileName, uuid, title,
                                                                                                description, memberNo, Boolean.FALSE);

        return ResponseEntity.ok(ApiResponse.success("전송 성공", "서버 전송 완료"));

    }

    // 숏폼 삭제
    @DeleteMapping("/short-form-firebase/{shortFormNo}")
    public ResponseEntity<ApiResponse> deleteShortFormWithFirebase(@RequestHeader("Authentication") String token, @PathVariable("shortFormNo") Long shortFormNo) {

        DeleteShortFormResponse response = shortFormFirebaseService.deleteShortForm(shortFormNo);

        return ResponseEntity.ok(ApiResponse.success("숏폼 삭제 성공 (firebase)", response));
    }
}
