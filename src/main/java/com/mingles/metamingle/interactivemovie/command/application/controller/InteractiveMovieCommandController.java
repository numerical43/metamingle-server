package com.mingles.metamingle.interactivemovie.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mingles.metamingle.auth.JwtTokenProvider;
import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.common.ApiStatus;
import com.mingles.metamingle.interactivemovie.command.application.dto.VideoMetadata;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InteractiveMovieCommandController {

    private final InteractiveMovieCommandService interactiveMovieCommandService;
    private final JwtTokenProvider jwtTokenProvider;

//    @PostMapping(value = "/interactive-movie", consumes = {"multipart/form-data"})
//    public ResponseEntity<ApiResponse> createInteractiveMovie(@RequestPart("video") List<MultipartFile> videos,
//                                                              @RequestPart("title") String title,
//                                                              @RequestPart("description") String description,
//                                                              @RequestPart("choice") List<String> choices) throws JCodecException, IOException {
//
//        List<CreateInteractiveMovieResponse> response = interactiveMovieCommandService.createInteractiveMovie(videos, title, description, choices);
//
//        return ResponseEntity.ok(new ApiResponse(ApiStatus.SUCCESS, "인터랙티브 무비 생성 성공", response));
//    }

    @PostMapping(value = "/interactive-movie", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse> createInteractiveMovie(@RequestHeader("Authorization") String token,
                                                              @RequestPart("video1") MultipartFile video1,
                                                              @RequestPart("video2") MultipartFile video2,
                                                              @RequestPart("video3") MultipartFile video3,
                                                              @RequestPart("title") String title,
                                                              @RequestPart("description") String description,
                                                              @RequestPart("choice1") String choice1,
                                                              @RequestPart("choice2") String choice2) throws JCodecException, IOException {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);

        List<MultipartFile> videos = Arrays.asList(video1, video2, video3);

        List<String> choices = Arrays.asList(choice1, choice2);

        List<CreateInteractiveMovieResponse> response = interactiveMovieCommandService.createInteractiveMovie(videos, title, description, choices, memberNo);

        return ResponseEntity.ok(new ApiResponse(ApiStatus.SUCCESS, "인터랙티브 무비 생성 성공", response));

    }


//    @PostMapping(value = "/interactive-movie")
//    public ResponseEntity<ApiResponse> createInteractiveMovie(@RequestBody VideoData data) throws JCodecException, IOException {
//        List<String> fileContents = data.getFiles();
//        VideoMetadata videoMetadata = data.getVideoMetadata();
//
//        List<MultipartFile> videos = null;
//
//        for (int i = 0; i < fileContents.size(); i++) {
//            String base64Video = fileContents.get(i);
//
//            byte[] videoBytes = Base64.getDecoder().decode(base64Video);
//
//            MultipartFile multipartFile =
//                    new MockMultipartFile("file", "video" + i + ".mp4", "video/mp4", videoBytes);
//
//            videos.add(multipartFile);
//        }
//
//        List<CreateInteractiveMovieResponse> response = interactiveMovieCommandService.createInteractiveMovie(videos, videoMetadata.getTitle(), videoMetadata.getDescription(),videoMetadata.getChoices());
//
//        return ResponseEntity.ok(new ApiResponse(ApiStatus.SUCCESS, "인터랙티브 무비 생성 성공", response));
//
//    }

//    @PostMapping(value = "/interactive-movie")
//    public ResponseEntity<ApiResponse> createInteractiveMovie(@RequestPart("files") MultipartFile[] files,
//                                                              @RequestPart("metadata") String metadataJson) throws JCodecException, IOException {
//
//        List<MultipartFile> videos = new ArrayList<>(Arrays.asList(files));
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        VideoMetadata videoMetadata = objectMapper.readValue(metadataJson, VideoMetadata.class);
//
//        String title = videoMetadata.getTitle();
//        String description = videoMetadata.getDescription();
//        List<String> choices = videoMetadata.getChoices();
//
//        List<CreateInteractiveMovieResponse> response = interactiveMovieCommandService.createInteractiveMovie(videos, title, description, choices);
//
//        return ResponseEntity.ok(new ApiResponse(ApiStatus.SUCCESS, "인터랙티브 무비 생성 성공", response));
//
//    }
}

