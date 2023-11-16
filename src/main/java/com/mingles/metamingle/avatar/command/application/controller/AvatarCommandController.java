package com.mingles.metamingle.avatar.command.application.controller;

import com.mingles.metamingle.auth.JwtTokenProvider;
import com.mingles.metamingle.avatar.command.application.dto.request.AvatarCommandRequest;
import com.mingles.metamingle.avatar.command.application.dto.response.AvatarCommandResponse;
import com.mingles.metamingle.avatar.command.application.service.AvatarCommandService;
import com.mingles.metamingle.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AvatarCommandController {

    private final AvatarCommandService avatarCommandService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/avatar")
    public ResponseEntity<ApiResponse> createAvatar(@RequestHeader("Authentication") String token,
                                                    @RequestBody AvatarCommandRequest request) {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);

        AvatarCommandResponse response = avatarCommandService.createAvatar(request, memberNo);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("아바타가 성공적으로 저장되었습니다." , response)
        );
    }
}
