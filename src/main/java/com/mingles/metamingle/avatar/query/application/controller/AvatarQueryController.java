package com.mingles.metamingle.avatar.query.application.controller;

import com.mingles.metamingle.auth.JwtTokenProvider;
import com.mingles.metamingle.avatar.query.application.service.AvatarQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AvatarQueryController {

    private final JwtTokenProvider jwtTokenProvider;
    private final AvatarQueryService avatarQueryService;

    @GetMapping("/avatar")
    public byte[] GetAvatar(@RequestHeader("Authentication") String token) {

        Long memberNo = jwtTokenProvider.getMemberNoFromToken(token);

        byte[] avatarBytes = avatarQueryService.getAvatarDataBytes(memberNo);

        return avatarBytes;
    }
}
