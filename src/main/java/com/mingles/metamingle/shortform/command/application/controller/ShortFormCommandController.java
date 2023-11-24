package com.mingles.metamingle.shortform.command.application.controller;

import com.google.protobuf.Api;
import com.mingles.metamingle.auth.JwtTokenProvider;
import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.shortform.command.application.dto.response.CreateShortFormResponse;
import com.mingles.metamingle.shortform.command.application.dto.response.DeleteShortFormResponse;
import com.mingles.metamingle.shortform.command.application.service.ShortFormFirebaseService;
import lombok.RequiredArgsConstructor;
import org.jcodec.api.JCodecException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class ShortFormCommandController {

    //    private final ShortFormCommandService shortFormCommandService;
    private final ShortFormFirebaseService shortFormFirebaseService;

    private final JwtTokenProvider jwtTokenProvider;

    // 숏폼 생성
//    @PostMapping("/short-form")
//    public ResponseEntity<ApiResponse> createShortForm(@RequestPart("video") MultipartFile video,
//                                               @RequestPart("title") String title) throws Exception {
//
//        CreateShortFormResponse response = shortFormCommandService.createShortForm(video, title);
//
//        return ResponseEntity.ok(ApiResponse.success("숏폼 저장 성공", response));
//
//    }

//    // 숏폼 생성
//    @PostMapping("/short-form-firebase")
//    public ResponseEntity<ApiResponse> createShortFormWithFirebase(@RequestPart("video") MultipartFile video,
//                                                                   @RequestPart("title") String title,
//                                                                   @RequestPart("description") String description,
//                                                                   @RequestPart("uuid") String uuid) throws Exception {
//
//
////        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);
//        Long memberNo = 1L;
//
//        CreateShortFormResponse response = shortFormFirebaseService.createShortForm(video, title, description, memberNo);
//
//        return ResponseEntity.ok(ApiResponse.success("숏폼 저장 성공 (firebase)", response));
//
//    }

    // 숏폼 생성
    @PostMapping("/short-form-firebase")
    public ResponseEntity<ApiResponse> createShortFormWithFirebase(@RequestHeader("Authentication") String token,
                                                                   @RequestPart("video") MultipartFile video,
                                                                   @RequestPart("title") String title,
                                                                   @RequestPart("description") String description,
                                                                   @RequestPart("uuid") String uuid) throws JCodecException, IOException, InterruptedException {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);

        // 비동기 처리를 위해서 MultipartFile을 byte[]로 변환
        byte[] videoBytes = video.getBytes();
        String fileName = video.getOriginalFilename();

        CreateShortFormResponse response = shortFormFirebaseService.createShortFormWithSubtitle(videoBytes, fileName, title, description, memberNo, Boolean.FALSE);

        return ResponseEntity.ok(ApiResponse.success("전송 성공", null));

//        ResponseEntity.ok(ApiResponse.success("전송 성공", null));
//
//        CompletableFuture.runAsync(() -> {
//            try {
//                Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);
//                CreateShortFormResponse response = shortFormFirebaseService.createShortFormWithSubtitle(video, title, description, memberNo, Boolean.FALSE);
//            } catch (IOException | JCodecException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        return ResponseEntity.ok(ApiResponse.success("숏폼 저장 성공 (firebase)", "완료"));

//        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);
//        CreateShortFormResponse response = shortFormFirebaseService.createShortFormWithSubtitle(video, title, description, memberNo, Boolean.FALSE);
//
//
//        return ResponseEntity.ok(ApiResponse.success("숏폼 저장 성공 (firebase)", response));

    }

    // 숏폼 삭제
    @DeleteMapping("/short-form-firebase/{shortFormNo}")
    public ResponseEntity<ApiResponse> deleteShortFormWithFirebase(@RequestHeader("Authentication") String token, @PathVariable("shortFormNo") Long shortFormNo) {

        DeleteShortFormResponse response = shortFormFirebaseService.deleteShortForm(shortFormNo);

        return ResponseEntity.ok(ApiResponse.success("숏폼 삭제 성공 (firebase)", response));
    }
}
