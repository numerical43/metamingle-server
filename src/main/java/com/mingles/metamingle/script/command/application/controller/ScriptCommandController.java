package com.mingles.metamingle.script.command.application.controller;

import com.mingles.metamingle.common.ApiResponse;
import com.mingles.metamingle.member.query.application.service.MemberQueryService;
import com.mingles.metamingle.script.command.application.dto.request.CreateScriptRequest;
import com.mingles.metamingle.script.command.application.dto.response.ScriptCommandResponse;
import com.mingles.metamingle.script.command.application.service.ScriptCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "AI 생성 대본 Command API")
@RestController
@RequiredArgsConstructor
public class ScriptCommandController {

    private final ScriptCommandService scriptCommandService;
    private final MemberQueryService memberQueryService;

    @Operation(summary = "AI 대본 생성")
    @PostMapping("api/script")
    public ResponseEntity<ApiResponse> createScript(@RequestHeader Map<String, String> requestHeader,
                                                    @RequestBody CreateScriptRequest request) {

//        String header = requestHeader.get("auth");
//        Long providerId = jwtTokenService.getUserIdFromToken(header);

        Long memberNo = memberQueryService.findMemberNoByProviderId("111111");

        ScriptCommandResponse response = scriptCommandService.createScript(memberNo, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success("성공적으로 저장되었습니다." , response)
        );
    }

}
