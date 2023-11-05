package com.mingles.metamingle.member.command.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.member.command.application.dto.request.RegistMemberRequest;
import com.mingles.metamingle.member.command.application.dto.response.MemberCommandResponse;
import com.mingles.metamingle.member.command.application.service.MemberCommandService;
import com.mingles.metamingle.member.query.application.dto.request.LoginRequest;
import com.mingles.metamingle.member.query.application.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberCommandController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/member/signup")
    public ResponseEntity<ApiResponse> Signup(@RequestBody RegistMemberRequest request) {

        MemberCommandResponse response = memberCommandService.signup(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("성공적으로 회원가입되었습니다." , response)
        );
    }
}
