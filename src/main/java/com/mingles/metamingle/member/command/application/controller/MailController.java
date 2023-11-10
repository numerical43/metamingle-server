package com.mingles.metamingle.member.command.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.member.command.application.dto.request.MailAuthRequest;
import com.mingles.metamingle.member.command.application.dto.response.MailAuthResponse;
import com.mingles.metamingle.member.command.infrastructure.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/member/email")
    public ResponseEntity<ApiResponse> mailAuthentication(@RequestBody MailAuthRequest request) {

        String verifiedCode = mailService.sendMail(request.getEmail());

        MailAuthResponse response = MailAuthResponse.from(verifiedCode);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("인증코드가 성공적으로 발급되었습니다." , response)
        );
    }
}
