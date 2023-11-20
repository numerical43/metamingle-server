package com.mingles.metamingle.shortform.command.application.controller;

import com.google.protobuf.Api;
import com.mingles.metamingle.auth.JwtTokenProvider;
import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.shortform.command.application.dto.response.CreateShortFormResponse;
import com.mingles.metamingle.shortform.command.application.dto.response.DeleteShortFormResponse;
import com.mingles.metamingle.shortform.command.application.service.ShortFormFirebaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<ApiResponse> createShortFormWithFirebase(@RequestPart("video") MultipartFile video,
                                                                   @RequestPart("title") String title,
                                                                   @RequestPart("description") String description,
                                                                   @RequestPart("uuid") String uuid) throws Exception {


//        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);
        Long memberNo = 1L;

        CreateShortFormResponse response = shortFormFirebaseService.createShortFormWithSubtitle(video, title, description, memberNo, Boolean.FALSE);

        return ResponseEntity.ok(ApiResponse.success("숏폼 저장 성공 (firebase)", response));

    }

    // 숏폼 삭제
    @DeleteMapping("/short-form-firebase/{shortFormNo}")
    public ResponseEntity<ApiResponse> deleteShortFormWithFirebase(@PathVariable("shortFormNo") Long shortFormNo) {

        DeleteShortFormResponse response = shortFormFirebaseService.deleteShortForm(shortFormNo);

        return ResponseEntity.ok(ApiResponse.success("숏폼 삭제 성공 (firebase)", response));
    }
}
