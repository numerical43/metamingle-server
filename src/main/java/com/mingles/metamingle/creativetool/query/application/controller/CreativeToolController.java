package com.mingles.metamingle.creativetool.query.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.creativetool.query.application.dto.request.ImageRequest;
import com.mingles.metamingle.creativetool.query.application.dto.request.SoundRequest;
import com.mingles.metamingle.creativetool.query.application.dto.response.ImageResponse;
import com.mingles.metamingle.creativetool.query.application.dto.response.SoundResponse;
import com.mingles.metamingle.creativetool.query.application.service.CreativeToolService;
import com.mingles.metamingle.member.query.application.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/creative")
@RequiredArgsConstructor
public class CreativeToolController {

    private final CreativeToolService creativeToolService;

    @GetMapping("/creative/image")
    public ResponseEntity<ApiResponse> FindImagesByLocation(@RequestBody ImageRequest request) {

        List<ImageResponse> response = creativeToolService.findImagesByLocation(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("성공적으로 조회되었습니다." , response)
        );
    }

    @GetMapping("/creative/sound")
    public ResponseEntity<ApiResponse> FindSoundsByGenre(@RequestBody SoundRequest request) {

        List<SoundResponse> response = creativeToolService.findSoundsByGenre(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("성공적으로 조회되었습니다." , response)
        );
    }
}
