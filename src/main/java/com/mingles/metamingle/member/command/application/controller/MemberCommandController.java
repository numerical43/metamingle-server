package com.mingles.metamingle.member.command.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.member.command.application.dto.request.MailAuthRequest;
import com.mingles.metamingle.member.command.application.dto.request.RegistMemberRequest;
import com.mingles.metamingle.member.command.application.dto.request.RegistMemberTemp;
import com.mingles.metamingle.member.command.application.dto.response.MailAuthResponse;
import com.mingles.metamingle.member.command.application.dto.response.MemberCommandResponse;
import com.mingles.metamingle.member.command.application.service.MemberCommandService;
import com.mingles.metamingle.member.command.infrastructure.service.MailService;
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
    private final MailService mailService;

    @PostMapping("/member/signup/email")
    public ResponseEntity<ApiResponse> mailAuthentication(@RequestBody MailAuthRequest request) {

        String verifiedCode = mailService.sendMail(request.getEmail());

        MailAuthResponse response = MailAuthResponse.from(verifiedCode);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("인증코드가 성공적으로 발급되었습니다." , response)
        );
    }

    @PostMapping("/member/signup")
    public ResponseEntity<ApiResponse> Signup(@RequestBody RegistMemberTemp request) {

        System.out.println("request.getPassword() = " + request.getPassword());

        MemberCommandResponse response = memberCommandService.signup(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("회원가입이 완료되었습니다." , response)
        );

    }


    /* Email 본인인증 후 회원가입
    @PostMapping("/member/signup")
    public ResponseEntity<ApiResponse> Signup(@RequestBody RegistMemberRequest request) {

        if(mailService.verifyCode(request.getEmail(), request.getCode())) {

            MemberCommandResponse response = memberCommandService.signup(request);

            return ResponseEntity.status(HttpStatus.OK).body(
                    ApiResponse.success("회원가입이 완료되었습니다." , response)
            );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.error("인증에 실패하였습니다.", request.getEmail())
        );
    }
     */


}
