package com.mingles.metamingle.interactivemovie.command.application.controller;

import com.mingles.metamingle.global.auth.JwtTokenProvider;
import com.mingles.metamingle.global.common.ApiResponse;
import com.mingles.metamingle.global.common.ApiStatus;
import com.mingles.metamingle.interactivemovie.command.application.dto.response.CreateInteractiveMovieResponse;
import com.mingles.metamingle.interactivemovie.command.application.service.InteractiveMovieCommandService;
import lombok.RequiredArgsConstructor;
import org.jcodec.api.JCodecException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InteractiveMovieCommandController {

    private final InteractiveMovieCommandService interactiveMovieCommandService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/interactive-movie", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse> createInteractiveMovie(@RequestHeader("Authentication") String token,
                                                              @RequestPart("video1") MultipartFile video1,
                                                              @RequestPart("video2") MultipartFile video2,
                                                              @RequestPart("video3") MultipartFile video3,
                                                              @RequestPart("title") String title,
                                                              @RequestPart("description") String description,
                                                              @RequestPart("choice1") String choice1,
                                                              @RequestPart("choice2") String choice2,
                                                              @RequestPart("uuid") String uuid)
                                                              throws JCodecException, IOException, InterruptedException {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);
//        Long memberNo = 1L;

        List<byte[]> videos = Arrays.asList(video1.getBytes(), video2.getBytes(), video3.getBytes());
        List<String> videosName = Arrays.asList(video1.getOriginalFilename(), video2.getOriginalFilename(), video3.getOriginalFilename());
        List<String> choices = Arrays.asList(choice1, choice2);

        List<CreateInteractiveMovieResponse> response = interactiveMovieCommandService
                .createInteractiveMovieWithSubtitle(videos, videosName, uuid, title, description, choices, memberNo);

        return ResponseEntity.ok(new ApiResponse(ApiStatus.SUCCESS, "인터랙티브 무비 생성 성공", "서버 전송 완료"));

    }

}

